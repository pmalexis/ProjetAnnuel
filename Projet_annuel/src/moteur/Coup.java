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
}
