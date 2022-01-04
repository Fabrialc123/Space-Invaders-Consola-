package Juego;

import java.util.Scanner;

import Comandos.Command;
import Comandos.CommandGenerator;
import Excepciones.CommandExecuteException;
import Excepciones.CommandParseException;
import Utilidades.BoardPrinter;
import Utilidades.GamePrinter;

public class Controller {
	private Game game;
	private Scanner in;
	private GamePrinter printer;
	
	//Constructor
	public Controller(Game game,Scanner in) {
		this.game = game;
		this.in = in;
		printer = new BoardPrinter();
	}
	
	
	public void run() {
		
		boolean end;
		String [] command;
		Command c;
		
		end = false;
		System.out.println(printer.toString(game));
		while(!end) {
			System.out.println("Command > ");
			command = this.in.nextLine().toLowerCase().split(" ");
			try {
			 c = CommandGenerator.parseCommand(command);
			 if (c != null) {
				if( c.execute(game)) {
					game.update();
					System.out.println(printer.toString(game));
				}
			 }
					if (game.isFinished()) {
						end = true;
						System.out.println(game.getWinnerMessage());
					}
			} catch (CommandParseException | CommandExecuteException | ArrayIndexOutOfBoundsException ex) {
				System.out.format("Error: " + ex.getMessage() + " %n");
			}
		}
	}
}
