package Comandos;

import Excepciones.CommandExecuteException;
import Excepciones.CommandParseException;
import Juego.Game;

public class MoveCommand extends Command {
	private int numCas;
	public MoveCommand() {
		super("Move", "M", "[M]ove right/left 1/2: ", "Move right/left the UCM-Ship");
	}
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.move(this.numCas);
			return true;
		} catch (CommandExecuteException e) {
			throw e;
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command c = null;
		if(matchCommandName(commandWords[0])) {
			try {
			if (commandWords.length == 3) {
				if(commandWords[1].equals("right")) {
					if(commandWords[2].equals("1") || commandWords[2].equals("2")) {
						c = this;
						this.numCas = Integer.parseInt(commandWords[2]);
					}
					else {
						throw new CommandParseException("Invalid number of cells");
					}
				}else if(commandWords[1].equals("left")) {
					if(commandWords[2].equals("1") || commandWords[2].equals("2")) {
					c = this;
					this.numCas = Integer.parseInt(commandWords[2]) * -1;
					}
					else {
						throw new CommandParseException("Invalid number of cells");
					}
				}
				else {
					throw new CommandParseException("Invalid direction");
				}				
				}
			else {
				throw new CommandParseException("Invalid format");
			}
			} catch (CommandParseException e) {
				throw e;
			}
		}

		return c;	
	}


}
