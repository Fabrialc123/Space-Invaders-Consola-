package Personajes;

public class GameObjectBoard {
	private GameObject[] objects;
	private int currentObjects;
	
	public GameObjectBoard (int width, int height) {
		objects = new GameObject[width*height];
		this.currentObjects = 0;
	}
	
	private int getCurrentObjects () {
		return this.currentObjects;
	}
	
	public void add (GameObject object) {
		this.objects[currentObjects] = object;
		this.currentObjects++;
	}
	
	private GameObject getObjectInPosition (int fil,int col ) {
		GameObject aux = null;
		int j = getIndex(fil,col);
		if(j != -1) {
			aux = objects[j];
		}
		return aux;
	}
	
	private int getIndex(int fil,int col ) {
		int i = 0;
		boolean enc = false;
		while(i < getCurrentObjects() && !enc) {
			if(objects[i].isOnPosition(fil, col)) {
				enc = true;
			}else i++;
		}
		if(!enc) i = -1;
		
		return i;
	}

	private void remove (GameObject object) {
		int i = 0;
		boolean enc = false;
		while(i <getCurrentObjects() && !enc) {
			if(object.equals(objects[i])) {
				enc = true;
				for(int j = i; j < getCurrentObjects() - 1;j++) {
					objects[j] = objects[j+1];
				}
				this.currentObjects--;
			}
			
			else i++;
		}
	}
	
	public void update() {
		for(int i = 0; i < this.currentObjects ; i++) {
			objects[i].update();
			checkAttacks(objects[i]);
		}
		removeDead();
	}
	
	public void shockwave(int dmg) {
		for(int i = 0; i < getCurrentObjects();i++) {
			objects[i].receiveShockWaveAttack(dmg);
		}
	}
	
	private void checkAttacks(GameObject object) {
		int i = 0;
		int fil,col;
		fil = object.getFil();
		col = object.getCol();
		while(i < getCurrentObjects() && object.isAlive()) {
			if(this.objects[i].isOnPosition(fil, col) && !(objects[i].equals(object))){
					object.performAttack(objects[i]);
			}
			i++;
		}
	}
	
	public void computerAction() {
		for(int i = 0; i < this.currentObjects; i++) {
			objects[i].computerAction();
		}
		removeDead(); //Se hace el remove deads em el caso que una nave se transforme 
	}
	
	private void removeDead() {
		for(int i = 0; i < this.currentObjects;i++) {
			if(!(objects[i].isAlive())) {
				objects[i].onDelete();
				remove(objects[i]);
				i = -1;									// Se resetea por si explota una nave explosiva y destruye a algún objeto ubicado antes que ella.
			}
		}
	}

	public String toString( int fil,int col ) {
		String a = "";
		Object aux = getObjectInPosition(fil,col);
		if(aux != null) {
			a = aux.toString();
		}
		return a;
	}

	public void shipExplodes(int fil, int col) {
		int dirCol [] = {1,1,1,0,0,-1,-1,-1};
		int dirFil [] = {-1,0,1,-1,1,-1,0,1};
		GameObject aux;
		for (int i = 0; i < 8; i++ ) {
			aux = getObjectInPosition(fil + dirFil[i], col + dirCol[i]);
			if (aux != null) {
				if (!aux.receiveBombAttack(1)) aux.receiveMissileAttack(1);
			}
		}
	}

	public String toSerialize() {
		String str = "";
		for(int i = 0; i < getCurrentObjects();i++) {
			str += objects[i].serialize();
		}
		
		return str;
	}

}

