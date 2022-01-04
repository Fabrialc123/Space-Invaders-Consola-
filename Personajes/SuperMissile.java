package Personajes;

import Juego.Game;

public class SuperMissile extends Weapon {

	public SuperMissile(Game game, int fil, int col, int live, int damage) {
		super(game, fil, col, live, damage);
	}

	@Override
	public void computerAction() {
		
	}

	@Override
	public void onDelete() {
		
	}

	@Override
	public void move() {
		this.fil--;
		
	}

	@Override
	public String serialize() {
		return "S;" + this.col + "," + this.fil + "\n";
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
	
	public String toString() {
		return "++";
	}

}
