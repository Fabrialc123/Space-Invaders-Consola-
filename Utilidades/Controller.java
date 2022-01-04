package Utilidades;

import java.util.Scanner;

import Juego.Game;

public class Controller {
	private Game game;
	private Scanner in;
	
	//Constructor
	public Controller(Game game,Scanner in) {
		this.game = game;
		this.in = in;
	}
	
	
	public void run() {
		
		boolean end,repintar;
		String [] command;
		int mov;
		
		end = false;
		repintar = true;
		boolean update = false;
		int finpartida;				// Indica quien ha ganado
		
		
		while(!end) {
			if(repintar) {
				System.out.println(this.game);
				repintar = false;
			}
			System.out.println("Command > ");
			command = this.in.nextLine().toLowerCase().split(" ");
			if(command[0].equals("move") || command[0].equals("m")) {
				if(command.length == 3) {
					if(command[1].equals("left") || command[1].equals("right")) {
						if(command[2].equals("1") || command[2].equals("2")) {
							mov = Integer.parseInt(command[2]);				// Cambiar string a entero //
							if (game.move(command[1],mov)) {
								update = true;							  
							}
							else {
								System.out.println("Error, la nave ha llegado al limite \n");
							}
						}else {
							System.out.println("Error con el número de casillas a mover \n");
						}
					}else {
						System.out.println("Error con la dirección \n ");
					}				
				}else {
					System.out.println("Error con el formato \n");
				}
			}else if(command[0].equals("shoot") || command[0].equals("s")) {
				if(this.game.dispararJugador()) {
					update = true;
				}else {
					System.out.println("Error, ya hay un misil en el tablero \n");
				}
			}
			
			else if(command[0].equals("list") || command[0].equals("l")) {
				System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: 2 \n" 
						+ "[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1 \n"
						+ "[O]vni: Points: 25 - Harm: 0 - Shield: 1 \n "
						+ "^__^: Harm: 1 - Shield: 3");
				
			}else if(command[0].equals("shockwave") || command[0].equals("w")) {
				if(this.game.shockwave()) {					
					update = true;
				}else {
					System.out.println("Error, ShockWave no disponible \n");
				}
			}
			
			else if(command[0].equals("help") || command[0].equals("h")) {
				System.out.println(" move: <left|right><1|2>: Mueve UCM-Ship hacia la direccion indicada \n shoot: UCM-Ship lanza un misil \n"
						+ "shockWave: UCM-Ship lanza un ShockWave \n list: Imprime por pantalla la lista de todas las naves en el tablero"
						+ "\n reset: reincia la partida \n help: Muestra este mensaje de ayuda \n exit: Termina la partida \n "
						+ "[none]:Salta un ciclo ");
				
			}else if(command[0].equals("") || command[0].equals("n") ) {
				update = true;
				
			}
			
			else if (command[0].equals("exit") || command[0].equals("E")) {
				end = true;
			}
			
			else if (command[0].equals("reset") || command[0].equals("R")) {
				this.game.iniciarJuego();
				repintar = true;			// Aqui  Update != true porque si no avanzaria de ciclo
			}
			
			else {
				System.out.println("Error, comando no válido \n");
			}
			
			
			if(update) {
				finpartida = this.game.update();
				repintar = true;
				update = false;
				if (finpartida > 0) {
					end = true;
					System.out.println(this.game);
					if (finpartida == 1) {
						System.out.println("PLAYER WINS! \n");
					}else if (finpartida >= 2) {
						System.out.println("ALIENS WINS! \n");
					}
				}
			}
		}
	}


}
