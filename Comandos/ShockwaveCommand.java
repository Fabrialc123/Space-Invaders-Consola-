package Comandos;

import Excepciones.CommandExecuteException;
import Juego.Game;

public class ShockwaveCommand extends Command {

	public ShockwaveCommand() {
		super("Shockwave", "W", "Shock[W]ave: ", "Use the shockwave");
	}
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
		game.shockWave();
		return true;
		}
		catch (CommandExecuteException e) {
			throw e;
		}
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
