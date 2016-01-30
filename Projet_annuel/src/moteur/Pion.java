package moteur;

import java.util.List;

public class Pion extends Pieces {

	public Pion(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "P");
		
		AjoutCoups();
	}

	@Override
	public List<Coup> ListeCoupPossible(List<Coup> ListeCoup, Plateau plateau) {
		return null;
	}
	
	public void AjoutCoups() {
		if (this.getCouleur() == Couleur.Noir) {
			ListCoup.add(new Coup(this.getPositionX() + Cardinal.Nord.getPosX(), this.getPositionY() + Cardinal.Nord.getPosY()));
		} else {
			ListCoup.add(new Coup(this.getPositionX() + Cardinal.Sud.getPosX(), this.getPositionY() + Cardinal.Sud.getPosY()));
		}
	}
	
	public void AjouterCoup() {
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.Est.getPosX(), this.getPositionY() + Cardinal.Est.getPosY()));
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.Ouest.getPosX(), this.getPositionY() + Cardinal.Ouest.getPosY()));
	}
}
