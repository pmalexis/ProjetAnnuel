package moteur;

import java.util.List;

public class Garde extends Pieces {
	
	public Garde (int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "G");
		
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
					} else if (Distance(c) > 2) {
						listeCoup.remove(i);
					}
				}
			}
		} else {			
			for (int i = listeCoup.size()-1; i >= 0; i--) {
				Coup c = listeCoup.get(i);
				if (c.getPosX() > 9 || c.getPosX() < 0 || c.getPosY() > 10 || c.getPosY() < 0) {
					listeCoup.remove(i);
				} else {
					if (c.getPosX() <= 6 || c.getPosY() <= 2 || c.getPosY() >= 6) {
						listeCoup.remove(i);
					} else if (plateau.getCase(c.getPosX(), c.getPosY()).getCouleur() == Couleur.Rouge) {
						listeCoup.remove(i);
					} else if (Distance(c) > 2) {
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
		liste.add(new Coup(this.getPositionX() + Cardinal.NordEst.getPosX(), this.getPositionY() + Cardinal.NordEst.getPosY()));
		liste.add(new Coup(this.getPositionX() + Cardinal.SudEst.getPosX(), this.getPositionY() + Cardinal.SudEst.getPosY()));
		liste.add(new Coup(this.getPositionX() + Cardinal.NordOuest.getPosX(), this.getPositionY() + Cardinal.NordOuest.getPosY()));
		liste.add(new Coup(this.getPositionX() + Cardinal.SudOuest.getPosX(), this.getPositionY() + Cardinal.SudOuest.getPosY()));
	}
}
