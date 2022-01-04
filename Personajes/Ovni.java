package Personajes;

import Juego.Game;
import Utilidades.IExecuteRandomActions;

public class Ovni extends EnemyShip {
	
	private boolean enable;
	
	
	public Ovni (Game game, int fil,int col, int live,int puntos) {
		super(game,fil,col,live,puntos);
		this.enable = false;
	}
	
	
	public String toString() {
		return "O[" + this.live + "]"; 			
	}


	@Override
	public void computerAction() {
		if(!enable) {
			if(IExecuteRandomActions.canGenerateRandomOvni(game)) {
				this.enable = true;
				this.fil = 0;
				this.col = Game.DIM_X;
			}
		}
	}


	@Override
	public void onDelete() {
		game.addObject(new Ovni (game,-1,-1,1,this.puntos));
		game.enableShockWave();
		game.receivePoints(this.puntos); 
	}


	@Override
	public void move() {
		this.col--;
		if (isOut()) enable=false;
	}


	@Override
	protected void update() {
		if (enable)move();
	}
	
	
	public boolean receiveMissileAttack(int damage) {
		this.live-= damage;
		return true;
	}


	@Override
	public String serialize() {
		return "O;" + this.col + "," + this.fil + ";" + this.live + "\n";
	}
	
	
}