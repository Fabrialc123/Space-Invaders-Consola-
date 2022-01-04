package Personajes;

import java.util.Random;
import Juego.Level;

public class DestroyerShipList {
	private Random random;
	private int maxCOL; 
	private int maxFIL; 
	private int contador;
	private DestroyerShip lista[]; //Array de objetos
	private Level nivel;
	private BombsList listaBombas;
	
	//Constructor
	
	public DestroyerShipList (Level lvl, Random random , BombsList listaBombas , int maxCOL, int maxFIL) {	
		this.nivel = lvl;
		this.random = random;
		this.listaBombas = listaBombas;
		this.contador = 0;
		this.maxCOL = maxCOL;
		this.maxFIL = maxFIL;
		this.lista = new DestroyerShip[this.nivel.getNumDestructores()];
		
		int fila = this.nivel.getFilaInicialDestructores();
		int col = 4;
		if (this.nivel.getNumDestructores() > 2) {
			col = 3;
		}

		for (int i = 0; i < this.nivel.getNumDestructores();i++) {
			this.lista[i] = new DestroyerShip (fila, col );
			col++;
			this.contador++;
		}
	}
	
	public boolean vacia() {return (this.contador == 0);}
	
	public void borrar(int pos ) {
		for(int i = pos;i < this.contador - 1;i++) { 
			this.lista[i] = this.lista[i + 1];
		}
		this.contador--;
	}
	
	
	public boolean seEncuentraNave(int fil,int col) {
		boolean existe = false;
		int i = 0;
		while((!existe) && (i < this.contador)) {
			if((this.lista[i].getFil() == fil) && (this.lista[i].getCol() == col)) existe = true;
			else i++; 
		}
		
		return existe;
	}
	
	public String pintarNave(int fil,int col) {
		int i = 0;
		String nave = null;
		boolean enc = false;
		while(i < this.contador && !enc) {
			if(this.lista[i].getFil() == fil && this.lista[i].getCol() == col) {
				nave = this.lista[i].toString();
				enc = true;
			}else {
				i++;
			}
		}
		return nave;
	}
	
	public boolean algunaNaveEnElBorde(String direccion) {
		int i = 0;
		boolean enc = false;
		if(direccion.equals("left")) {
			while(!enc && i < this.contador) {
				if(this.lista[i].getCol() == 0) {
					enc = true;
				}else i++;
			}
		}else if(direccion.equals("right")) {
			while(!enc && i < this.contador) {
				if(this.lista[i].getCol() == maxCOL - 1) {
					enc = true;
				}else i++;
			}
		}else if(direccion.equals("down")) {
			while(!enc && i < this.contador) {
				if(this.lista[i].getFil() == maxFIL - 1) {
					enc = true;
				}else i++;
			}
		}
		return enc;

	}
	
	
	public void moverNaves(String direccion) {
		for(int i = 0; i < this.contador;i++) {
			this.lista[i].mover(direccion);
		}
	}
	
	public int ataca(int fila, int columna, int dmg) {
		int puntos = 0;
		boolean atacada = false;
		int i = 0;
		while((!atacada) && (i < this.contador)) {
			if((this.lista[i].getFil() == fila) && (this.lista[i].getCol() == columna)) {
				this.lista[i].atacar(dmg);
				atacada = true;	
			}
			else i++; 
		}
		if(this.lista[i].getResistencia() <= 0) {
			puntos = this.lista[i].getPuntos();
			borrar(i);
			
		}
		
		return puntos;
	}
	
	
	public int atacarTodo(int dmg) {
		int puntos = 0;
		for(int i = 0; i < this.contador; i++) {
			this.lista[i].atacar(dmg);
			if(this.lista[i].getResistencia() <= 0) {
				puntos += this.lista[i].getPuntos();
				borrar(i);
				i--;
			}
		}
		return puntos;
	}
	
	public int getContador() {
		return this.contador;
	}

	public void soltarBombas() {
		for (int i = 0; i < this.contador;i++) {
			if (this.random.nextDouble() < this.nivel.getFrecDisparo()) {
				if(this.lista[i].soltarBomba()) {
					this.listaBombas.agregarBomba(this.lista[i].getBomba());
				}
			}
		}	
	}
	
	public void quitarBombadeDestroyer(int fila, int columna) {
		int i = 0;
		boolean quitado = false;
		
		while ((i < this.contador) && (!quitado)) {
			if (this.lista[i].tieneBomba()) {	
				if ((this.lista[i].getFilBomba() == fila) && (this.lista[i].getColBomba() == columna)) {				
					this.lista[i].quitarBomba();
				}else {i++;}
			}else {i++;}
		}
		
	}
	
	public void eliminarBombasPasadas () {
		int i=0;
		while (i < this.maxCOL) {
			quitarBombadeDestroyer(maxFIL,i);
			this.listaBombas.eliminarPorCoor(maxFIL, i);
			i++;
		}
	}
}
