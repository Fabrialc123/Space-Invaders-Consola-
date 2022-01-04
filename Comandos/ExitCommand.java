package Comandos;

import Juego.Game;

public class ExitCommand extends Command {

	public ExitCommand() {
		super("Exit", "E", "[E]xit: ", "Close the program");
	}
	@Override
	public boolean execute(Game game) {
		game.exit();
		return true;
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
