package Personajes;

import Juego.Game;
import Utilidades.IAttack;


public abstract class GameObject implements IAttack {
	protected int fil;
	protected int col;
	protected int live;
	protected Game game;
	
	public GameObject( Game game, int fil,int col, int live) {
		this.fil = fil;
		this.col = col;
		this.game = game;
		this.live = live;
	}
	
	public int getFil() {
		return this.fil;
	}
	
	public int getCol() {
		return this.col;
	}

	public boolean isAlive() {
		return this.live > 0;
	}

	public int getLive() {
		return this.live;
	}
	
	public boolean isOnPosition( int fil,int col ) {
		return (this.fil == fil && this.col == col) ;
	}

	public void getDamage (int damage) {
		this.live = damage >= this.live ? 0 : this.live - damage;
	}
	
	public boolean isOut() {
		return !game.isOnBoard( this.fil,this.col );
	}

	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String serialize();
	protected abstract void update();
}