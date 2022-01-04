package Personajes;

import Juego.Game;

public class UCMMissile extends Weapon {

	public UCMMissile(Game game, int fil, int col, int live, int damage) {
		super(game, fil, col, live, damage);
	}


	@Override
	public void computerAction() {	
	}

	@Override
	public void onDelete() {
		game.enableMissile();	
	}

	@Override
	public void move() {
		this.fil--;
		
	}

	@Override
	public String toString() {
		return "oo";
	}


	@Override
	protected void update() {
		move();
	}
	
	public boolean performAttack(GameObject other) {
		other.receiveMissileAttack(this.damage);
		this.live --;
		return true;
		}
	
	public boolean receiveBombAttack(int damage) {
		this.live -= damage;
		return true;
	}


	@Override
	public String serialize() {
		return "M;" + this.col + "," + this.fil + "\n";
	};
	
	

}
