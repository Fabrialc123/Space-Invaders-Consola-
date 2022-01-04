package Personajes;

import Juego.Game;
import Utilidades.IExecuteRandomActions;

public class DestroyerShip extends AlienShip {

	private Bomba bomba;
	
	public DestroyerShip(Game game,int fila,int columna,int vida,int puntos) {
		super(game,fila,columna,vida,puntos);
	}
	
	
	public String toString() {
		return "D[" + this.live + "]"; 
	}



	@Override
	public void computerAction() {
		if (puedeLanzarBomba()) {
			if (IExecuteRandomActions.canGenerateRandomBomb(game)) {
				LanzaBomba();
			}
		}
		
	}
	
	private void LanzaBomba() {
		bomba = new Bomba (game, this.fil, this.col, 1, 1, this);
		game.addObject(bomba);
	}


	private boolean puedeLanzarBomba(){
		return this.bomba == null;
	}


	public void eliminarBomba() {
		this.bomba = null;
	}


	@Override
	public String serialize() {
		int ciclospormover = game.getCycle() % game.getLevel().getNumCyclesToMoveOneCell();	
		String mibomba = "";
		if (!puedeLanzarBomba()) mibomba =  "      " + this.bomba.serializedelanzador();
		return "D;" + this.col + "," + this.fil + ";" + this.live + ";" + ciclospormover + ";" + movimiento + mibomba + "\n" ;
	}




}
