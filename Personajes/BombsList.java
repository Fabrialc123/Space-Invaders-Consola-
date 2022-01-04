package Personajes;

public class BombsList {
	private int contador;
	private Bomba lista[]; 
	
	public BombsList (int maxBombas) {
		this.contador = 0;
		this.lista = new Bomba[maxBombas];
	}
	
	public void agregarBomba (Bomba bomba) {
		this.lista[this.contador] = bomba;
		this.contador++;
	}
	public void borrarBomba (int pos) {
		
		for(int i = pos;i < this.contador - 1;i++) {
			this.lista[i] = this.lista[i + 1];
		}
		this.contador--;
	}
	
	public void moverBombas() {
		for (int i = 0;i < this.contador; i++) {
			this.lista[i].mover();
		}
	}

	public boolean seEncuentraBomba(int fila, int columna) {
		boolean existe = false;
		int i = 0;
		while((!existe) && (i < this.contador)) {
			if((this.lista[i].getFila() == fila) && (this.lista[i].getColumna() == columna)) existe = true;
			else i++; 
		}
		return existe;
	}
	
	public int getContador() {
		return this.contador;
	}

	public void eliminarPorCoor(int fila, int columna) {
		boolean borrado = false;
		int i = 0;
		while((!borrado) && (i < this.contador)) {
			if((this.lista[i].getFila() == fila) && (this.lista[i].getColumna() == columna)) {
				borrarBomba(i);
				borrado = true;
			}
			else i++; 
		}
	}
	public int getDmg(int fila,int columna) {
		boolean enc = false;
		int i = 0;
		int dmg = 0;
		while((!enc) && (i < this.contador)) {
			if((this.lista[i].getFila() == fila) && (this.lista[i].getColumna() == columna)) {
				dmg = this.lista[i].getDmg();
				enc = true;
			}
			else i++; 
		}
		return dmg;
	}
}
