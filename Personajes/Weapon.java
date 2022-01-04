package Personajes;

import Juego.Game;

public abstract class Weapon extends GameObject {
	
	protected int damage;
	
	public Weapon(Game game, int fil, int col, int live,int damage) {
		super(game, fil, col, live);
		this.damage = damage;
	}
	
	public int getDmg() {
		return this.damage;
	}
	
	public boolean isAlive () {
		return (!isOut() && this.live > 0);			// Se sobreescribe para agregar la condición de que se salga del tablero
	}

}
