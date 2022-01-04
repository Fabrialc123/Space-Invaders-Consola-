package Juego;

import Juego.Level;
import Personajes.BombsList;
import Personajes.DestroyerShipList;
import Personajes.RegularShipList;
import Personajes.UCMShip;
import Utilidades.GamePrinter;
import Personajes.Misil;
import Personajes.Ovni;
import java.util.Random;

public class Game {
	private Random random;
	private Level nivel;
	private Ovni ovni;
	private RegularShipList listaRegulares;
	private DestroyerShipList listaDestructores;
	private BombsList listaBombas;
	private UCMShip naveJugador;
	private int numeroCiclos;
	private String direccionNaves;
	private Misil ucmMisil;
	private int puntos;
	private boolean shockwave;
	
	public static final int MAXCOL = 9;
	public static final int MAXFIL = 8;
	
	public Game (Level level,long seed) {
		this.nivel = level;
		this.random = new Random (seed);
		iniciarJuego();						// Llama a una funcion que comparte con el comando reset
	}
	
	public void iniciarJuego() {
		this.listaRegulares = new RegularShipList(nivel ,MAXCOL, MAXFIL);
		this.listaBombas = new BombsList (nivel.getNumDestructores());
		this.listaDestructores = new DestroyerShipList(nivel,random, listaBombas, MAXCOL,MAXFIL);
		this.naveJugador = new UCMShip();
		this.numeroCiclos = 0;
		this.direccionNaves = "left";						 //Direccion inicial
		this.ucmMisil = null;
		this.puntos = 0;
		this.shockwave = false;
		this.ovni = null;
		OvniAparece();
	}

	public String characterAtToString(int i, int j) {
		String pintar = "";
		if(listaRegulares.seEncuentraNave(i,j)) {
			pintar = listaRegulares.pintarNave(i, j);
		}else if(listaDestructores.seEncuentraNave(i, j)) {
			pintar = listaDestructores.pintarNave(i, j);
		}else if(naveJugador.getFila() == i && naveJugador.getColumna() == j) {
			pintar = naveJugador.toString();
		}else if(existeMisil() && (this.ucmMisil.getFila() == i && this.ucmMisil.getColumna() == j)) {
			pintar = this.ucmMisil.toString();
		}else if(listaBombas.seEncuentraBomba(i, j)) {
			pintar =  ".";
		}else if (existeOvni() && ( 0 == i) && (this.ovni.getCol() == j )) {
			pintar = this.ovni.toString();
		}
		return pintar;
	}

	public int update() {
		int fin = 0;
			////// 	AUMENTA CICLO	//////
		this.numeroCiclos++;
		
			//////	MUEVE MISIL	 //////
		
		if(existeMisil()) {
			this.ucmMisil.mover();
			if(this.ucmMisil.getFila() < 0) {
				this.ucmMisil = null;
			}else {
				misilImpacta();
			}
		}
		
			//////	MUEVE NAVES ALIENIGENAS	 //////
		
		moverNaves();	
		
			//////	SUELTA BOMBAS 	//////
		
		if (listaDestructores.getContador() > 0) {
			listaDestructores.soltarBombas();
		}
		
			//////	MUEVE BOMBAS 	//////
		
		this.listaBombas.moverBombas();
		this.listaDestructores.eliminarBombasPasadas(); 							// La función está en ListaDestructores porque así las bombas se eliminan tanto de su array como de la nave que la lanzó
		 if (this.listaBombas.seEncuentraBomba(this.naveJugador.getFila(),this.naveJugador.getColumna())) {
			 this.naveJugador.atacado(this.listaBombas.getDmg(this.naveJugador.getFila(), this.naveJugador.getColumna()));
			 this.listaDestructores.quitarBombadeDestroyer(this.naveJugador.getFila(), this.naveJugador.getColumna());
			 this.listaBombas.eliminarPorCoor(this.naveJugador.getFila(),this.naveJugador.getColumna());
		 }
		
		 	//////	APARACE EL OVNI / MUEVE EL OVNI	//////
		 
		if (existeOvni()) {
			this.ovni.mover();
			if (this.ovni.getCol() < 0) {
				this.ovni = null;
			}
		}else {
		OvniAparece();
		}
		
		
		
		if(existeMisil()) {						// Vuelve a comprobar porque se han movido naves.
			misilImpacta();
		}
		
			//////	DETERMINAR GANADOR	//////
		
		if (contarAlienigenas() == 0) {
			fin = 1;							// Gana el jugador :)
		}else if (this.naveJugador.getVida() <= 0) {
			fin = 2;							// Ganan los aliens :(
		}else if (listaDestructores.algunaNaveEnElBorde("down") || listaRegulares.algunaNaveEnElBorde("down")) {
			this.naveJugador.atacado(this.naveJugador.getVida());
			fin = 3;
		}
		
		return fin;
	}
	
	public String toString() {
		String tablero;
		GamePrinter gp = new GamePrinter(this,MAXFIL,MAXCOL);
		
		tablero = "Life: " + this.naveJugador.getVida() + "\n" 
		+ "Cycles: "+this.numeroCiclos + "\n"
		+ "Points: " + this.puntos +  "\n" 
		+ "Aliens: " + contarAlienigenas() + "\n"
		+ "ShockWave: " + this.shockwave + gp;
 		
		return tablero;
	}

	public boolean move(String direccion,int numCasillas) {
		boolean movido = false;
		if(direccion.equals("left")) {
			if(naveJugador.getColumna() - numCasillas >= 0) {
				naveJugador.mover(direccion,numCasillas);
				movido = true;
			}
		}else if(naveJugador.getColumna() + numCasillas < MAXCOL) {
			naveJugador.mover(direccion, numCasillas);
			movido = true;
		}
		
		return movido;
	}
	
	public int getNumeroCiclos() {
		return this.numeroCiclos;
	}
	
	public boolean dispararJugador() {
		boolean disparado = false;
		if(!existeMisil()) {
			this.ucmMisil = new Misil(this.naveJugador.getFila(),this.naveJugador.getColumna());
			disparado = true;
		}
		return disparado;
	}
	
	public boolean shockwave() {
		boolean ShockWaveDisparado = false;
		if (hayShockwave()) {
			this.shockwave = false;
			ShockWaveDisparado = true;
			int dmgSW = 1;
			this.puntos+= listaRegulares.atacarTodo(dmgSW);
			this.puntos += listaDestructores.atacarTodo(dmgSW);
			if (existeOvni()) {
				this.puntos += this.ovni.getPuntos();
				this.shockwave = true;
				this.ovni = null;
			}
		}
		return ShockWaveDisparado;
	}
	
	
	private boolean existeMisil() {
		return this.ucmMisil != null;
	}
	
	private boolean hayShockwave() {
		return this.shockwave;
	}
	
	private int contarAlienigenas() {
		return listaRegulares.getContador() + listaDestructores.getContador();
	}
	
	private void misilImpacta () {
		int FilaUCMMisil = this.ucmMisil.getFila();
		int ColUCMMisil = this.ucmMisil.getColumna();
		if(listaRegulares.seEncuentraNave(FilaUCMMisil,ColUCMMisil)) {
			this.puntos += listaRegulares.ataca(FilaUCMMisil,ColUCMMisil,this.ucmMisil.getDmg());
			this.ucmMisil = null;
		}else if(listaDestructores.seEncuentraNave(FilaUCMMisil,ColUCMMisil)) {
			this.puntos += listaDestructores.ataca(FilaUCMMisil,ColUCMMisil,this.ucmMisil.getDmg());
			this.ucmMisil = null;
		}else if (this.listaBombas.seEncuentraBomba(FilaUCMMisil,ColUCMMisil)) {
			 this.listaDestructores.quitarBombadeDestroyer(FilaUCMMisil, ColUCMMisil);
			 this.listaBombas.eliminarPorCoor(FilaUCMMisil,ColUCMMisil);
			 this.ucmMisil = null;
		 }
		else if (ovni != null) {
			if ((FilaUCMMisil == 0) && (ColUCMMisil == this.ovni.getCol())) {
				this.puntos += this.ovni.getPuntos();
				this.shockwave = true;
				this.ovni = null;
				this.ucmMisil = null;
			}
		}
	}

	private void moverNaves() {
		int frecuencia = this.nivel.getFrecMovimiento();	
		
		if(listaRegulares.algunaNaveEnElBorde(this.direccionNaves) || listaDestructores.algunaNaveEnElBorde(this.direccionNaves)) {
			listaRegulares.moverNaves("down");
			listaDestructores.moverNaves("down");
			if(this.direccionNaves.equals("left")) {
				this.direccionNaves = "right";
			}else this.direccionNaves = "left";
			
		}else if(this.numeroCiclos % frecuencia == 0) {
			listaRegulares.moverNaves(this.direccionNaves);
			listaDestructores.moverNaves(this.direccionNaves);
		}
	}
	
	private boolean existeOvni() {
		return (this.ovni != null);
	}
	
	private void OvniAparece() {
		if (random.nextDouble() <= this.nivel.getFrecOvni()) {
				ovni = new Ovni (MAXCOL - 1);
		}
	}


}
