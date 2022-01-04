package Comandos;

import Juego.Game;
import Utilidades.BoardPrinter;
import Utilidades.GamePrinter;

public class ResetCommand extends Command {
	private GamePrinter printer;
	public ResetCommand() {
		super("Reset", "R", "[R]eset: ", "Restart the game");
		printer = new BoardPrinter();
	}
	@Override
	public boolean execute(Game game) {
		game.initGame();
		System.out.println(printer.toString(game));
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		Command c = null;
		if (matchCommandName(commandWords[0])) {
			c = this;
		}
		return c;	
	}

}
