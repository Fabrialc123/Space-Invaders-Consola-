package Comandos;

import Excepciones.CommandParseException;

public class CommandGenerator {
	private static Command[] availableCommands = {
			new ListCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new MoveCommand(),
			new ShockwaveCommand(),
			new ShootCommand(),
			new BuySuperMissileCommand(),
			new SaveCommand(),
			new StringifyCommand(),
			new ListPrintersCommand()
			
	};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		int i = 0;
		Command c = null;
		try {
		while( (i < availableCommands.length) && c == null) {
			c = availableCommands[i].parse(commandWords);
			i++;
		}
			if (c == null) {
				throw new CommandParseException ("Command not exists");
			}
		}
		catch (CommandParseException e) {
			throw e;
		}
		return c;
	}
	
	public static String commandHelp() {
		String help = "";
		for (int i = 0;i < availableCommands.length;i++) {
			help += availableCommands[i].helpText();
		}	
		return help;
		
	}
}
