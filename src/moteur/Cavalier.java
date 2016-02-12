package moteur;

import java.util.List;

public class Cavalier extends Pieces {
	
	public Cavalier(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "C");
		
		AjoutCoups();
	}

	public List<Coup> getListeCoupPossible(List<Coup> listeCoup, Plateau plateau) {
		
		//Supprime tous les coups hors plateau
		for(int i=listeCoup.size()-1;i>=0;i--) {
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
		listeCoup = plateau.estEchec(this, listeCoup);
		//Supprime tous les coups avec une pièce entre le cavalier et sa destination		
		return PieceAuMilieu(listeCoup, plateau);
	}

	public void AjoutCoups() {
		List<Coup> liste = this.getListCoup();
		liste.clear();
		liste.add(new Coup(this.getPositionX() + (2 * Cardinal.NordEst.getPosX()), this.getPositionY() + Cardinal.NordEst.getPosY()));
		liste.add(new Coup(this.getPositionX() + (2 * Cardinal.NordOuest.getPosX()), this.getPositionY() + Cardinal.NordOuest.getPosY()));
		liste.add(new Coup(this.getPositionX() + (2 * Cardinal.SudEst.getPosX()), this.getPositionY() + Cardinal.SudEst.getPosY()));
		liste.add(new Coup(this.getPositionX() + (2 * Cardinal.SudOuest.getPosX()), this.getPositionY() + Cardinal.SudOuest.getPosY()));
		liste.add(new Coup(this.getPositionX() + Cardinal.NordEst.getPosX(), this.getPositionY() + (2 * Cardinal.NordEst.getPosY())));
		liste.add(new Coup(this.getPositionX() + Cardinal.NordOuest.getPosX(), this.getPositionY() + (2 * Cardinal.NordOuest.getPosY())));
		liste.add(new Coup(this.getPositionX() + Cardinal.SudEst.getPosX(), this.getPositionY() + (2 * Cardinal.SudEst.getPosY())));
		liste.add(new Coup(this.getPositionX() + Cardinal.SudOuest.getPosX(), this.getPositionY() + (2 * Cardinal.SudOuest.getPosY())));
	}
	
	private List<Coup> PieceAuMilieu (List<Coup> listeCoup, Plateau plateau) {
		for(int i=0;i<listeCoup.size();i++) {
			boolean bool = false;
			
			if(listeCoup.get(i).getPosX() > this.getPositionX()) {
				if(listeCoup.get(i).getPosY() > this.getPositionY()) {
					if(listeCoup.get(i).getPosX()-1 == this.getPositionX() && listeCoup.get(i).getPosY()-2 == this.getPositionY()) {
						if(plateau.getCase(this.getPositionX(), this.getPositionY()+1).getType() != Type.SansPiece) bool = true;
					}
					else if(listeCoup.get(i).getPosX()-2 == this.getPositionX() && listeCoup.get(i).getPosY()-1 == this.getPositionY()) {
						if(plateau.getCase(this.getPositionX()+1, this.getPositionY()).getType() != Type.SansPiece) bool = true;
					}
				}
				else {
					if(listeCoup.get(i).getPosX()-1 == this.getPositionX() && listeCoup.get(i).getPosY()+2 == this.getPositionY()) {
						if(plateau.getCase(this.getPositionX(), this.getPositionY()-1).getType() != Type.SansPiece) bool = true;
					}
					else if(listeCoup.get(i).getPosX()-2 == this.getPositionX() && listeCoup.get(i).getPosY()+1 == this.getPositionY()) {
						if(plateau.getCase(this.getPositionX()+1, this.getPositionY()).getType() != Type.SansPiece) bool = true;
					}
				}
			}
			else {
				if(listeCoup.get(i).getPosY() > this.getPositionY()) {
					if(listeCoup.get(i).getPosX()+1 == this.getPositionX() && listeCoup.get(i).getPosY()-2 == this.getPositionY()) {
						if(plateau.getCase(this.getPositionX(), this.getPositionY()+1).getType() != Type.SansPiece) bool = true;
					}
					else if(listeCoup.get(i).getPosX()+2 == this.getPositionX() && listeCoup.get(i).getPosY()-1 == this.getPositionY()) {
						if(plateau.getCase(this.getPositionX()-1, this.getPositionY()).getType() != Type.SansPiece) bool = true;
					}
				}
				else {
					if(listeCoup.get(i).getPosX()+1 == this.getPositionX() && listeCoup.get(i).getPosY()+2 == this.getPositionY()) {
						if(plateau.getCase(this.getPositionX(), this.getPositionY()-1).getType() != Type.SansPiece) bool = true;
					}
					else if(listeCoup.get(i).getPosX()+2 == this.getPositionX() && listeCoup.get(i).getPosY()+1 == this.getPositionY()) {
						if(plateau.getCase(this.getPositionX()-1, this.getPositionY()).getType() != Type.SansPiece) bool = true;
					}
				}
			}
				
			if(bool) {
				listeCoup.remove(i);
				i--;
			}
		}
		return listeCoup;
	}
}
