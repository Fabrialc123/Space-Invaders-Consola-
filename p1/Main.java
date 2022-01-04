package p1;

import java.util.Scanner;

import Juego.Game;
import Juego.Level;
import Utilidades.Controller;

public class Main {

	public static void main(String[] args) {
		long seed;								// Es un long, porque System.currentTimeMillis() devuelve en ese formato
		Level nivel = Level.valueOf(args[0]);
		if (args.length == 2 ) {
			seed = Integer.parseInt(args[1]);
		}else seed = System.currentTimeMillis();
		Game game = new Game(nivel,seed);		 	//Nivel, numero aleatorio
		Scanner in = new Scanner(System.in);
		Controller c = new Controller(game,in);
		c.run();
	
				
	}

}
