package moteur;

public enum Couleur {
	Rouge ('R'),
	Noir ('N'),
	Aucune (' ');
	
	private final char couleur;
	
	private Couleur (char couleur) {
		this.couleur = couleur;
	}
	
	public char getCouleur() {
		return this.couleur;
	}
	
	public boolean oppose (Couleur c) {
		if (this.getCouleur() != c.getCouleur() && this.getCouleur() != ' ') {
			return true;
		}
		return false;
	}
}