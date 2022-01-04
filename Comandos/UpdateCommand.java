package Comandos;

import Juego.Game;

public class UpdateCommand extends Command {

	public UpdateCommand() {
		super("", " ", "[None]: ", "Update one cycle");
	}
	@Override
	public boolean execute(Game game) {
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
