package Personajes;

import Juego.Game;

public abstract class AlienShip extends EnemyShip{

	protected static boolean algunaEnElBorde; //Cambiar
	protected static boolean algunaHaAterrizado;
	protected static int navesVivas = 0;
	protected static int navesPorMover;
	protected static int movimiento = -1;
	protected static int abajo = 0;
	
	
	public AlienShip(Game game, int fil, int col, int live, int puntos) {
		super(game, fil, col, live, puntos);
		algunaEnElBorde = false;
		algunaHaAterrizado = false;
		navesVivas += 1;
		navesPorMover += 1;
	}
	
	public static int numNaves() {
		return navesVivas;
	}
	
	public static boolean allDead() {
		return navesVivas == 0;
	}
	
	public static boolean haveLanded() {
		return algunaHaAterrizado;
	}
	
	public static void iniNaves() {
		navesVivas = 0;
		navesPorMover = 0;
	}
	
	public void move() {
	if((navesPorMover == navesVivas) && algunaEnElBorde) {
		abajo = 1;
	} 
		movimiento();
		if(navesPorMover == 0) {
			navesPorMover = navesVivas;
			if(abajo == 1) {
				algunaEnElBorde = false;
				abajo = 0;
				if(movimiento == -1) movimiento = 1;
				else movimiento = -1;
			}
		}
		
	}
	
	
	
	private void movimiento() {
		if(abajo == 1) {
			this.fil++;
			if(this.fil == Game.DIM_Y - 1) algunaHaAterrizado = true;
		}else {this.col+=movimiento;
			if(this.col <= 0 || this.col >= Game.DIM_Y) {
				algunaEnElBorde = true;
			}
		}
		navesPorMover --;
	}
	
	public void onDelete() {
		navesVivas--;
		navesPorMover--;
		game.receivePoints(this.puntos); 
	}
	public void update() {
		if (algunaEnElBorde || (( game.getCycle() % game.getLevel().getNumCyclesToMoveOneCell()) == 0)) move();
	}
	
	public static void setAlgunaEnElBorde(boolean algunaEnElBorde) {
		AlienShip.algunaEnElBorde = algunaEnElBorde;
	}

	public static void setAlgunaHaAterrizado(boolean algunaHaAterrizado) {
		AlienShip.algunaHaAterrizado = algunaHaAterrizado;
	}

	public static boolean isAlgunaEnElBorde() {
		return algunaEnElBorde;
	}

	public static boolean isAlgunaHaAterrizado() {
		return algunaHaAterrizado;
	}
	
}
