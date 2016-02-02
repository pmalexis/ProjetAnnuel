package moteur;

public class Coup {
	
	protected int posX;
	protected int posY;
	
	public Coup (int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public boolean equals (Coup c) {
		return (posX == c.posX && posY == c.posY);
	}
	
	public String toString() {
		return "pos : {"+ posX + "; " + posY + "}";
	}
}
