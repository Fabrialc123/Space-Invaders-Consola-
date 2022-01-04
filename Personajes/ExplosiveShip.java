package Personajes;

import Juego.Game;

public class ExplosiveShip extends AlienShip {

	
	public ExplosiveShip(Game game,int fila,int columna,int vida,int puntos, boolean algunaEnElBorde, boolean algunaHaAterrizado) {
		super(game,fila,columna,vida,puntos);
		super.setAlgunaEnElBorde(algunaEnElBorde);
		super.setAlgunaHaAterrizado(algunaHaAterrizado);
	}
	public String toString() {
		return "E" + "[" + this.live + "]";
	}


	@Override
	public void computerAction() {

	}




	@Override
	public String serialize() {
		int ciclospormover = game.getCycle() % game.getLevel().getNumCyclesToMoveOneCell();	
		return "E;" + this.col + "," + this.fil + ";" + this.live + ";" + ciclospormover + ";" + movimiento  + "\n";
	}

	public void onDelete() {
		navesVivas--;
		navesPorMover--;
		game.receivePoints(this.puntos); 
		game.shipExplodes(this.fil,this.col);
	} 
}
