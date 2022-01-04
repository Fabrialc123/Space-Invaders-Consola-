package Personajes;

import Juego.Game;

public abstract class EnemyShip extends Ship{
	protected int puntos;
	
	
	public EnemyShip ( Game game, int fil,int col, int live,int puntos) {
		super(game,fil,col,live);
		this.puntos = puntos;
	}
	
	
	public int getPuntos() {
		return this.puntos;
	}
	
	public boolean receiveShockWaveAttack(int damage) {
		this.live -= damage;
		return true;
	}
	public boolean receiveMissileAttack(int damage) {
		this.live -= damage;
		return true;
	}
	
}
