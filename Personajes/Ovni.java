package Personajes;

public class Ovni {
	private int fila;
	private int col;
	private int resistencia;
	private int puntos;
	
	public Ovni (int col) {
		this.fila = 0;
		this.col = col;
		this.resistencia = 1;	
		this.puntos = 25;
	}
	
	public void mover() {
		this.col--;
	}
	
	public String toString() {
		return "O[" + this.resistencia + "]"; 			
	}
	
	public int getPuntos() {
		return this.puntos;
	}

	public int getCol() {
		return this.col;
	}
}