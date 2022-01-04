package Personajes;

import Juego.Game;

public abstract class Ship extends GameObject {
	
	public Ship( Game game, int fil,int col, int live) {
		super(game,fil,col,live);
	}
	
}
