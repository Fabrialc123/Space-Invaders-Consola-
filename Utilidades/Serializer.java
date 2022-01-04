package Utilidades;

import Juego.Game;

public class Serializer extends GamePrinter {

	public String toString(Game game) {
		return game.toSerialize();
	}

	@Override
	public String helpText() {
		// TODO Auto-generated method stub
		return null;
	}

}
