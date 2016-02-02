package moteur;

import java.util.List;

public class Roi extends Pieces {
	
	public Roi (int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "R");
		
		AjoutCoups();
	}
	
	public List<Coup> getListeCoupPossible(List<Coup> listeCoup, Plateau plateau) {
		
		if (this.getCouleur() == Couleur.Noir) {			
			for (int i = listeCoup.size()-1; i >= 0; i--) {
				Coup c = listeCoup.get(i);
				if (c.getPosX() > 9 || c.getPosX() < 0 || c.getPosY() > 10 || c.getPosY() < 0) {
					listeCoup.remove(i);
				} else {
					if (c.getPosX() >= 3 || c.getPosY() <= 2 || c.getPosY() >= 6) {
						listeCoup.remove(i);
					} else if (plateau.getCase(c.getPosX(), c.getPosY()).getCouleur() == Couleur.Noir) {
						listeCoup.remove(i);
					} else if (Distance(c) > 1) {
						listeCoup.remove(i);
					}
				}
			}
		} else {
			for (int i = 0; i < listeCoup.size(); i++) {
				System.out.println(listeCoup.get(i).getPosX() + " " + listeCoup.get(i).getPosY());
			}
			System.out.println("--------------------------------------------------------");
			for (int i = listeCoup.size()-1; i >= 0; i--) {
				Coup c = listeCoup.get(i);
				if (c.getPosX() > 9 || c.getPosX() < 0 || c.getPosY() > 10 || c.getPosY() < 0) {
					listeCoup.remove(i);
				} else {
					if (c.getPosX() <= 6 || c.getPosY() <= 2 || c.getPosY() >= 6) {
						listeCoup.remove(i);
					} else if (plateau.getCase(c.getPosX(), c.getPosY()).getCouleur() == Couleur.Rouge) {
						listeCoup.remove(i);
					} else if (Distance(c) > 1) {
						listeCoup.remove(i);
					}
				}
			}
		}
		return listeCoup;
	}

	public void AjoutCoups() {
		List<Coup> liste = this.getListCoup();
		liste.clear();
		
		liste.add(new Coup(this.getPositionX() + Cardinal.Nord.getPosX(), this.getPositionY() + Cardinal.Nord.getPosY()));
		liste.add(new Coup(this.getPositionX() + Cardinal.Sud.getPosX(), this.getPositionY() + Cardinal.Sud.getPosY()));
		liste.add(new Coup(this.getPositionX() + Cardinal.Est.getPosX(), this.getPositionY() + Cardinal.Est.getPosY()));
		liste.add(new Coup(this.getPositionX() + Cardinal.Ouest.getPosX(), this.getPositionY() + Cardinal.Ouest.getPosY()));
	}
}
