package Personajes;

import Juego.Game;

public class DeadShip extends Ship {

	public DeadShip(Game game, int fil, int col, int live) {
		super(game, fil, col, live);
	}
	
	public String toString() {
		return "!xx!";
	}

	@Override
	public void computerAction() {
		
	}

	@Override
	public void onDelete() {
		
	}

	@Override
	public void move() {
		
	}

	@Override
	public String serialize() {
		return null;// dado que esta clase se crea cuando el jugador se muere, no tiene sentido serializarlo ya que la partida ya habra acabado
	}

	@Override
	protected void update() {
		
	}

}
