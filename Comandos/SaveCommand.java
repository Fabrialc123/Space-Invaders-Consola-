package Comandos;

import java.io.IOException;

import Excepciones.CommandParseException;
import Juego.Game;

public class SaveCommand extends Command {
		private String file;
		public SaveCommand() {
			super("Save", "V", "Sa[V]e fileName: ", "Save the game in file fileName.dat");
		}
		@Override
		public boolean execute(Game game) {
			try {
				game.save(this.file);
				System.out.println("Succesfully saved in C:/hlocal/"+this.file+".dat");
			} catch (IOException e) {
					System.out.println("Failed to save");
			}
			return false;
		}

		@Override
		public Command parse(String[] commandWords) throws CommandParseException {
			Command c = null;
			if(matchCommandName(commandWords[0])) {
				try {
				if (commandWords.length == 2) {
					c = this;
					this.file = commandWords[1];
				}else {
					throw new CommandParseException ("Invalid format");
				}
				
				}
				catch (CommandParseException e) {
				throw e;
				}		
			}
			return c;	
		}


	}


