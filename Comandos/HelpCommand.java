package Comandos;

import Juego.Game;

public class HelpCommand extends Command {
	
	public HelpCommand() {
		super("Help", "H", "[H]elp: ", "Show this screen");
	}
	@Override
	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());
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
