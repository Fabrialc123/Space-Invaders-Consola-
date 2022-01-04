package Personajes;

import Juego.Game;

public class Bomba extends Weapon {
	private DestroyerShip lanzador;					
	
	public Bomba(Game game, int fil, int col, int live, int damage, DestroyerShip lanzador) {
		super(game, fil, col, live, damage);
		this.lanzador = lanzador;
	}

	public void move() {
		this.fil++;
	}

	@Override
	public void computerAction() {
		
	}

	@Override
	public void onDelete() {
		lanzador.eliminarBomba();
	}

	@Override
	protected void update() {
		move();
	}
	
	public boolean receiveMissileAttack(int damage) {
		this.live-= damage;
		return true;
	}
	
	public boolean performAttack(GameObject other) {
		other.receiveBombAttack(this.damage);
		this.live --;
		return true;
		}
	
	public String toString() {
		return "."; 
	}
	public String serialize () {
		return "";					// Su serialize esta vacio porque ya se encarga de ello su lanzador
	}
	public String serializedelanzador () {
		return "B;"+this.col+","+this.fil;
	}
}
