package Utilidades;

import Juego.Game;
import Juego.Level;
import Personajes.AlienShip;
import Personajes.DestroyerShip;
import Personajes.GameObject;
import Personajes.GameObjectBoard;
import Personajes.Ovni;
import Personajes.RegularShip;

//import
public class BoardInitializer {
	
	private Level level;
	private GameObjectBoard board;
	private Game game;
	
	public  GameObjectBoard initialize(Game game, Level level) {
		this.level = level;
		this.game = game;
		board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);
		
		initializeOvni();
		initializeRegularAliens();
		initializeDestroyerAliens();
		return board;
	}
	
	private void initializeOvni () {
		GameObject ovni = new Ovni(this.game,-1,-1, 1, 15);			// Empieza en -1,-1 porque asi no aparece en el tablero
		board.add(ovni);
	}

	private void initializeRegularAliens () {
		AlienShip.iniNaves();
		GameObject aux;
		int fila = 1;
		int filasNecesarias = level.getNumRowsOfRegularAliens();
		int AliensPorFila = level.getNumRegularAliensPerRow();
		while (fila <= filasNecesarias ) {
			for (int i = 0; i < AliensPorFila ; i++) {
				aux = new RegularShip (this.game,fila,i+3,2,5);
				board.add(aux);
			}
			fila++;
		}
	}
	
	private void initializeDestroyerAliens() {
		GameObject aux;
		int fila = 1 + level.getNumRowsOfRegularAliens();
		int AliensPorFila = level.getNumDestroyerAliensPerRow();
		int centrar = level.getNumRegularAliensPerRow() / level.getNumDestroyerAliensPerRow() - 1;
		for (int i = 0; i < AliensPorFila ; i++) {
				aux = new DestroyerShip (this.game,fila,i+3 + centrar,1,10);
				board.add(aux);
		}
	}
}
