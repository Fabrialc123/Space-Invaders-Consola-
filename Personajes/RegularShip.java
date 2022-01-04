package Personajes;

public class RegularShip {
	private int fila;
	private int columna;
	private int resistencia;
	private int puntos;
	
	public RegularShip(int fila,int columna) {
		this.fila = fila;
		this.columna = columna;
		this.resistencia = 2;
		this.puntos = 5;
	}
	
	
	public void mover(String direccion) {
		if(direccion.equals("left")) {
			this.columna--;
		}
		else if (direccion.equals("right")) {
			this.columna++;
		} else this.fila++;
	}
	
	public int getFil() {
		return this.fila;
	}
	
	public int getCol() {
		return this.columna;
	}
	
	public boolean CompararCoordenadas(int fil,int col) {
		return (this.fila == fil && this.columna == col );
	}
	
	public String toString() {
		return "R[" + resistencia + "]";
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
	

}