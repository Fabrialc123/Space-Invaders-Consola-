package Juego;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import Excepciones.CommandExecuteException;
import Personajes.AlienShip;
import Personajes.GameObject;
import Personajes.GameObjectBoard;
import Personajes.UCMShip;
import Utilidades.BoardInitializer;
import Utilidades.IPlayerController;


public class Game implements IPlayerController{
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;

	private int currentCycle;
	private Random rand;
	private Level level;

	GameObjectBoard board;

	private UCMShip player;
	
	private boolean doExit;
	private BoardInitializer initializer;
	
	public Game (Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
	}
	
	public void initGame () {
		currentCycle = 0;
		board = initializer.initialize(this, level);
		player = new UCMShip(this,DIM_Y - 1, DIM_X / 2,3, 0);
		board.add(player);
	}

	public Random getRandom() {
		return rand;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public int getCycle() {
		return this.currentCycle;
	}
	
	public void reset() {
		initGame();
	}
	
	public void addObject(GameObject object) {
		board.add(object);
	}
	
	public String positionToString(int fil,int col ) {
		return board.toString(fil,col);
	}

	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}
	
	public boolean aliensWin() {
		return !player.isAlive() || AlienShip.haveLanded();
	}
	
	private boolean playerWin () {
		return AlienShip.allDead();
	}
	
	public void update() {
		currentCycle += 1;
		board.computerAction();
		board.update();
	}
	
	public boolean isOnBoard( int fil,int col ) {
		return ((fil >= 0 && fil < DIM_Y) &&  (col >=0 && col < DIM_X)) ;
	}
	
	public void exit() {
		doExit = true;
	}
	
	public String infoToString() {
		String estado;
		estado = "Life:" + player.getLive() + "\n" 
		+ "Cycles: " + this.currentCycle + "\n" 
				+ "Points: " + this.player.getPuntos()+ "\n" 
		+ "Enemy Ships: " + this.getNumNaves() + "\n"
		+"ShockWave: " + this.player.hayShockwave() + "\n"
		+ "SuperMissiles: " + this.player.getSuperMisiles();
		
		return estado; 
	}
	
	public String getWinnerMessage () {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}

	@Override
	public void move(int numCells) throws CommandExecuteException {
		try {
			player.move(numCells);
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}
	
	private int getNumNaves() {
		return AlienShip.numNaves();
	}

	public void shootLaser() throws CommandExecuteException {
		try {
		 player.shoot();
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}
	
	public void shootSuperMissile() throws CommandExecuteException {
		try {
		 this.player.shootSuperMissile();
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}
	
	@Override
	public void shockWave() throws CommandExecuteException {
		try {
		if(player.hayShockwave()) {
			board.shockwave(1); 
		}
		else {
			throw new CommandExecuteException ("ShockWave not available");
		}
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}

	@Override
	public void receivePoints(int points) {
		player.recibirPuntos(points);
	}

	@Override
	public void enableShockWave() {
		this.player.enablesShockWave();
		
	}

	@Override
	public void enableMissile() {
		player.enableMissile();
	}
	
	public void buySuperMissile() throws CommandExecuteException {
		try {
		this.player.buySuperMissile();
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}

	public String characterAtToString(int i, int j) {
		return board.toString(i , j);
	}

	public void shipExplodes(int fil, int col) {
		board.shipExplodes(fil,col);
	}

	public String toSerialize() {
		return "— Space Invaders v2.0 —" + "\n" + "\n" 
				+ "G;" + this.currentCycle + "\n" 
				+ "L;" + this.level + "\n"
				+ board.toSerialize();
	}

	public void save(String file) throws IOException {
		File fichero = new File("C:/hlocal/" + file + ".dat");
		if(!fichero.exists()) {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(fichero));
			buffer.write(toSerialize());
			buffer.close();
		}
		
	}
}
