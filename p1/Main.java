package p1;

import java.util.Random;
import java.util.Scanner;

import Excepciones.MainFormatException;
import Juego.Controller;
import Juego.Game;
import Juego.Level;

public class Main {

	public static void main(String[] args) {
		try {
		long seed;								// Es un long, porque System.currentTimeMillis() devuelve en ese formato
		if (args.length == 0 || args.length > 2) {
			throw new MainFormatException("");
		}
		if (!(args[0].equals("EASY") || args[0].equals("HARD") || args[0].equals("INSANE"))) {
			throw new MainFormatException(": level must be one of: EASY, HARD, INSANE");
		}
		Level nivel = Level.valueOf(args[0]);
		if (args.length == 2 ) {
			seed = Integer.parseInt(args[1]);
		}else seed = System.currentTimeMillis();
		Random rand = new Random (seed);
		Game game = new Game(nivel,rand);		 	//Nivel, numero aleatorio
		Scanner in = new Scanner(System.in);
		Controller c = new Controller(game,in);
		c.run();
		}
		catch (MainFormatException ex) {
			System.out.format("Usage:  Main <EASY|HARD|INSANE> [seed]" + ex.getMessage() + " %n");
		}
		catch (NumberFormatException ex) {
			System.out.format("Usage:  Main <EASY|HARD|INSANE> [seed]: The seed must be a number \n");
		}
	
				
	}

}
