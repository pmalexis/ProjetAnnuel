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
}