package moteur;

import java.util.List;

public class Elephant extends Pieces {
	
	public Elephant(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "E");
		
		AjoutCoups();
	}
	
	public List<Coup> getListeCoupPossible(List<Coup> listeCoup, Plateau plateau) {
		for (int i = listeCoup.size()-1; i >= 0; i--) {
			Coup c = listeCoup.get(i);
			if (0 <= c.getPosX() && c.getPosX() < 10 && 0 <= c.getPosY() && c.getPosY() < 9) {
				//Gestion de la capture
				if (plateau.getCase(c.getPosX(), c.getPosY()).getCouleur() == this.getCouleur()) {
					listeCoup.remove(i);
				}
			} else {
				listeCoup.remove(i);
			}
		}
		if (this.getCouleur() == Couleur.Noir) {
			for (int i = listeCoup.size()-1; i >= 0; i--) {
				Coup c = listeCoup.get(i);			
				if (c.getPosX() > 4) {
					listeCoup.remove(i);
				}
			}
		} else {
			for (int i = listeCoup.size()-1; i >= 0; i--) {
				Coup c = listeCoup.get(i);			
				if (c.getPosX() < 5) {
					listeCoup.remove(i);
				}
			}
		}
		listeCoup = plateau.estEchec(this, listeCoup);
		return PieceAuMilieu(listeCoup, plateau);
	}

	public void AjoutCoups() {
		List<Coup> liste = this.getListCoup();
		liste.clear();
		liste.add(new Coup(this.getPositionX() + (Cardinal.NordEst.getPosX() * 2), this.getPositionY() + (Cardinal.NordEst.getPosY() * 2)));
		liste.add(new Coup(this.getPositionX() + (Cardinal.SudEst.getPosX() * 2), this.getPositionY() + (Cardinal.SudEst.getPosY() * 2)));
		liste.add(new Coup(this.getPositionX() + (Cardinal.NordOuest.getPosX() * 2), this.getPositionY() + (Cardinal.NordOuest.getPosY() * 2)));
		liste.add(new Coup(this.getPositionX() + (Cardinal.SudOuest.getPosX() * 2), this.getPositionY() + (Cardinal.SudOuest.getPosY() * 2)));
		
	}
	
	private List<Coup> PieceAuMilieu (List<Coup> listeCoup, Plateau plateau) {
		for(int i = listeCoup.size()-1; i >= 0; i--) {
			boolean bool = false;
			
			if(listeCoup.get(i).getPosX() > this.getPositionX()) {
				if(listeCoup.get(i).getPosY() > this.getPositionY()) {
					if(plateau.getCase(this.getPositionX()+1, this.getPositionY()+1).getType() != Type.SansPiece) bool = true;
				}
				else {
					if(plateau.getCase(this.getPositionX()+1, this.getPositionY()-1).getType() != Type.SansPiece) bool = true;
				}
			}
			else {
				if(listeCoup.get(i).getPosY() > this.getPositionY()) {
					if(plateau.getCase(this.getPositionX()-1, this.getPositionY()+1).getType() != Type.SansPiece) bool = true;
				}
				else {
					if(plateau.getCase(this.getPositionX()-1, this.getPositionY()-1).getType() != Type.SansPiece) bool = true;
				}
			}
				
			if(bool) {
				listeCoup.remove(i);
			}
		}
		return listeCoup;
	}
}
