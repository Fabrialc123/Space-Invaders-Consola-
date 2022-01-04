package Personajes;


public class DestroyerShip {
	private int fila;
	private int columna;
	private int resistencia;
	private int puntos;
	private Bomba bomba;
	
	public DestroyerShip(int fila,int columna) {
		this.fila = fila;
		this.columna = columna;
		this.resistencia = 1;
		this.puntos = 10;
		}
	
	
	public void mover(String direccion) {
		if(direccion.equals("left")) {
			this.columna--;
		}
		else if (direccion.equals("right")) {
			this.columna++;
		}else {
			this.fila++;
		}
	}
	
	public String toString() {
		return "D[" + resistencia + "]"; 
	}
	public int getFil() {
		return this.fila;
	}
	public int getCol() {
		return this.columna;
	}
	
	public void atacar(int dmg) {
		this.resistencia -= dmg;
		
	}
	public int getResistencia() {
		return this.resistencia;
	}
	
	public int getPuntos() {
		return this.puntos;
	}


	public boolean soltarBomba() {
		boolean bombaSoltada = false;
		if (this.bomba == null) {
			this.bomba = new Bomba(this.fila, this.columna);
			bombaSoltada = true;
		}
		return bombaSoltada;
	}
	
	public Bomba getBomba() {
		return this.bomba;
	}
	
	public void quitarBomba() {
		this.bomba = null;
	}


	public int getFilBomba() {
		return this.bomba.getFila();
	}
	
	public int getColBomba() {
		return this.bomba.getColumna();
	}


	public boolean tieneBomba() {
		return (this.bomba != null);
	}



}
