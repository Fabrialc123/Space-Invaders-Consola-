package Utilidades;

import Excepciones.CommandExecuteException;

public interface IPlayerController {
	
	// PLAYER ACTIONS	
	public void move (int numCells) throws CommandExecuteException;
	public void shootLaser() throws CommandExecuteException;
	public void shockWave() throws CommandExecuteException;
	
	// CALLBACKS
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableMissile();
	
}

