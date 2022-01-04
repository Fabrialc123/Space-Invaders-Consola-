package Comandos;

import Juego.Game;
import Utilidades.PrinterTypes;

public class ListPrintersCommand extends Command {
	public ListPrintersCommand() {
		super("ListPrinters", "P", "List[P]rinters: ", "Show the printers");
	}
	@Override
	public boolean execute(Game game) {
		System.out.println(PrinterTypes.printerHelp(game)); 
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
