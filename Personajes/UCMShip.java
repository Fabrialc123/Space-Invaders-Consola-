package Personajes;

import Excepciones.CommandExecuteException;
import Juego.Game;

public class UCMShip extends Ship{

	private boolean enableshockwave;
	private int puntos;
	private UCMMissile misil;
	private int supermisiles;
	
	public UCMShip(Game game,int fil,int col, int live,int supermisiles) {
		super(game,fil,col,live);
		this.enableshockwave = false;
		this.puntos = 0;
		this.misil = null;
		this.supermisiles = supermisiles;
	}
	
	public String toString() {
		return  "^__^";
	}
	
	
	public boolean hayShockwave() {
		return this.enableshockwave;
	}
	
	public void shoot() throws CommandExecuteException { 
		try {
			if(this.misil == null) {
				this.misil = new UCMMissile(this.game,this.fil,this.col,1,1);
				game.addObject(misil);
			}
			else {
				throw new CommandExecuteException("A missil is on board");
			}
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}
	

	@Override
	public void computerAction() {
		
	}
	@Override
	public void onDelete() {
		game.addObject(new DeadShip (this.game,this.fil,this.col,1));  			// Crea una nueva nave para que sea representada en el tablero una vez que esta sea eliminada.
	}
	public void move(int numCells) throws CommandExecuteException {
		try {
			if (game.isOnBoard(this.fil, this.col + numCells)) {
				this.col += numCells;
			}
			else {
				throw new CommandExecuteException ("Player is on the edge");
			}
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}

	@Override
	public void move() {
		
		
	}

	public void enablesShockWave() {
		this.enableshockwave = true;
		
	}
	public int getPuntos() {
		return this.puntos;
	}
	
	public int getSuperMisiles() {
		return this.supermisiles;
	}
	
	public void recibirPuntos(int puntos) {
		this.puntos += puntos;
	}

	@Override
	protected void update() {

		
	}

	public void enableMissile() {
		this.misil = null;
	}
	
	public boolean receiveBombAttack(int damage) {
		this.live -= damage;
		return true;
	}

	@Override
	public String serialize() {
		return "P;" + this.col +";" + this.col + ";" + this.live + ";" + this.puntos + ";" + this.supermisiles + "\n";
	}

	public void buySuperMissile() throws CommandExecuteException {
	try {
		if (this.puntos >= 20) {
			this.puntos -= 20;
			this.supermisiles++;
		 }
		else throw new CommandExecuteException ("More points required");
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}

	public void shootSuperMissile() throws CommandExecuteException {
		try {
			if (this.supermisiles >= 1) {
				game.addObject(new SuperMissile(this.game,this.fil,this.col,1, 2));
				this.supermisiles--;
			}
			else {
				throw new CommandExecuteException ("SuperMissile is not available");
			}
		}
		catch (CommandExecuteException e) {
			throw e;
		}
	}
	
	
}
