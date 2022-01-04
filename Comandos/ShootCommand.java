package Comandos;

import Excepciones.CommandExecuteException;
import Excepciones.CommandParseException;
import Juego.Game;

public class ShootCommand extends Command {
	private boolean shootSupermissile = false;
	public ShootCommand() {
		super("Shoot", "S", "[S]hoot / [S]hoot SuperMissile: ", "Shoot a missile or a super missile");
	}
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if (this.shootSupermissile) {
				this.shootSupermissile = false;
				game.shootSuperMissile();	
			}
			else {
				game.shootLaser();
			}
			return true;
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command c = null;
		if (matchCommandName(commandWords[0])) {
			try {
			if (commandWords.length >= 2) {
				if ("supermissile".equalsIgnoreCase(commandWords[1]) || "super".equalsIgnoreCase(commandWords[1])) {
					this.shootSupermissile = true;
				}
				else {
					throw new CommandParseException ("Invalid format");
				}
			}
			}
			catch (CommandParseException e) {
				throw e;
			}
			c = this;
		}
		return c;	
	}

}
