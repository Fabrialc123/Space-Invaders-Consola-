package Comandos;

import Excepciones.CommandExecuteException;
import Juego.Game;

public class BuySuperMissileCommand extends Command {
		public BuySuperMissileCommand() {
			super("Buy", "B", "[B]uy: ", "Buy a super missile");
		}
		@Override
		public boolean execute(Game game) throws CommandExecuteException {
			try {
			 game.buySuperMissile();
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

