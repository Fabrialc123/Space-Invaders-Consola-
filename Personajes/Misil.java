package Personajes;

public class Misil {
	private int fila;
	private int columna;
	private int damage;
	
	public Misil (int fila,int columna) {
		this.fila = fila;
		this.columna = columna;
		this.damage = 1;
	}
	
	public void mover() {
		this.fila--;
	}
	
	public String toString() {
		return "oo";
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
