package Personajes;

public class Bomba {
	private int fila;
	private int columna;
	private int damage;
	
	
	public Bomba (int fil, int columna) {
		this.fila = fil;
		this.columna = columna;
		this.damage = 1;
	}
	
	public void mover () {
		this.fila++;
	}

	public int getFila() {
		return this.fila;
	}
	
	public int getColumna() {
		return this.columna;
	}

	public int getDmg() {
		return this.damage;
	}
}
