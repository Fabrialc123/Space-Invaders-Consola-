package Personajes;

import Juego.Game;
import Utilidades.IExecuteRandomActions;

public class RegularShip extends AlienShip {
	
	
	public RegularShip(Game game,int fila,int columna,int vida,int puntos) {
		super(game,fila,columna,vida,puntos);
	}
	
	public String toString() {
		return "R"+ "[" + this.live + "]";
	}


	@Override
	public void computerAction() {
		if (IExecuteRandomActions.canTurnExplosive(game)) {
			game.addObject(new ExplosiveShip (game,this.fil,this.col,this.live,this.puntos, super.isAlgunaEnElBorde(),super.isAlgunaHaAterrizado()));
			this.live = -1;
			this.puntos = 0;
		}
	}

	@Override
	public String serialize() {
		int ciclospormover = game.getCycle() % game.getLevel().getNumCyclesToMoveOneCell();	
		return "R"+";" + this.col + "," + this.fil + ";" + this.live + ";" + ciclospormover + ";" + movimiento + "\n";
	}


} 

