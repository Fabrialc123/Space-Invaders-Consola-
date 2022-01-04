package Personajes;

public class UCMShip {
	private int fila;
	private int columna;
	private int resistencia;
	
	public UCMShip() {
		this.fila = 7;
		this.columna = 4;
		this.resistencia = 3;
	}
	public void mover(String direccion,int mov) {
		if(direccion.equals("left")) {
			this.columna -= mov;
		}else this.columna += mov;
		
	}
	
	public String toString() {
		String a;
		if(this.resistencia <= 0) {
			a = "!xx!";
			}else a = "^__^";
		return a;
	}
	public int getFila() {
		return this.fila;
	}
	public int getColumna() {
		return this.columna;
	}
	
	public int getVida() {
		return this.resistencia;
	}
	public void atacado(int damage) {
		this.resistencia -= damage;	
	}
	
}
