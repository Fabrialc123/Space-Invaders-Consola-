package Juego;



public enum Level {
		EASY, HARD, INSANE;
	

	public double getFrecOvni() {
		double frecuenciaOvni = 0;
		if (this== EASY) {
			frecuenciaOvni = 0.5;
		} else if (this == HARD) {
			frecuenciaOvni = 0.3;
		}else if ( this == INSANE) {
			frecuenciaOvni = 0.1;
		}
		return frecuenciaOvni;
	}

	public int getFrecMovimiento() {
		int frecuenciaMovimiento = 0;
		if (this == EASY) {
			frecuenciaMovimiento = 3;
		} else if (this == HARD) {
			frecuenciaMovimiento = 2;
		}else if ( this == INSANE) {
			frecuenciaMovimiento = 1;
		}
		return frecuenciaMovimiento;
	}

	public int getNumRegulares() {
		int numeroNavesRegulares = 0;
		if (this == EASY) {
			numeroNavesRegulares = 4;
		} else if (this == HARD) {
			numeroNavesRegulares = 8;
		}else if ( this == INSANE) {
			numeroNavesRegulares = 8;
		}
		return numeroNavesRegulares;
	}

	public int getFilaInicialRegulares() {
		int filaInicialRegulares = 0;
		if (this== EASY) {
			filaInicialRegulares = 1;
		} else if (this == HARD) {
			filaInicialRegulares = 1;
		}else if ( this == INSANE) {
			filaInicialRegulares = 1;
		}
		return filaInicialRegulares;
	}
	
	public int getNumDestructores() {
		int numeroNavesDestructores = 0;
		if (this== EASY) {
			numeroNavesDestructores = 2;
		} else if (this == HARD) {
			numeroNavesDestructores = 2;
		}else if ( this == INSANE) {
			numeroNavesDestructores = 4;
		}
		return numeroNavesDestructores;
	}

	public int getFilaInicialDestructores() {
		int filaInicialDestructores = 0;
		if (this== EASY) {
			filaInicialDestructores = 2;
		} else if (this == HARD) {
			filaInicialDestructores = 3;
		}else if ( this == INSANE) {
			filaInicialDestructores = 3;
		}
		return filaInicialDestructores;
	}

	public double getFrecDisparo() {
		double frecuenciaDisparo = 0;
		if (this== EASY) {
			frecuenciaDisparo = 0.1;
		} else if (this == HARD) {
			frecuenciaDisparo = 0.3;
		}else if ( this == INSANE) {
			frecuenciaDisparo = 0.5;
		}
		return frecuenciaDisparo;
	}
	 
}
