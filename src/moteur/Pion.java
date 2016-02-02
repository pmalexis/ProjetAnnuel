package moteur;

import java.util.List;

public class Pion extends Pieces {

	public Pion(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "P");
		
		AjoutCoups();
	}

	@Override
	public List<Coup> getListeCoupPossible(List<Coup> listeCoup, Plateau plateau) {
		for (int i = listeCoup.size() - 1; i >= 0; i--) {
			if (plateau.getCase(listeCoup.get(i).getPosX(), listeCoup.get(i).getPosY()).getCouleur() == this.getCouleur()) {
				listeCoup.remove(i);
			}
		}
		return listeCoup;
	}
	
	public void AjoutCoups() {
		List<Coup> liste = this.getListCoup();
		liste.clear();
		
		if (this.getCouleur() == Couleur.Rouge) {
			liste.add(new Coup(this.getPositionX() + Cardinal.Nord.getPosX(), this.getPositionY() + Cardinal.Nord.getPosY()));
		} else {
			liste.add(new Coup(this.getPositionX() + Cardinal.Sud.getPosX(), this.getPositionY() + Cardinal.Sud.getPosY()));
		}
	}
	
	public void AjouterCoup() {
		List<Coup> liste = this.getListCoup();
		liste.add(new Coup(this.getPositionX() + Cardinal.Est.getPosX(), this.getPositionY() + Cardinal.Est.getPosY()));
		liste.add(new Coup(this.getPositionX() + Cardinal.Ouest.getPosX(), this.getPositionY() + Cardinal.Ouest.getPosY()));
	}
}
