package Comandos;

import Juego.Game;
import Utilidades.GamePrinter;
import Utilidades.Serializer;

public class StringifyCommand extends Command{
	GamePrinter serial;
	public StringifyCommand() {
		super("Stringify","G", "Strin[G]ify: ", "Serialize the game as text");
		serial = new Serializer();
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(this.serial.toString(game));
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		Command c = null;
		if (matchCommandName(commandWords[0])) c = this;
		return c;
	}

}
