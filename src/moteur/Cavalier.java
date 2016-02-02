package moteur;

import java.util.List;

public class Cavalier extends Pieces {
	
	public Cavalier(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "C");
		
		AjoutCoups();
	}

	public List<Coup> ListeCoupPossible(List<Coup> listeCoup, Plateau plateau) {
		
		//Supprime tous les coups hors plateau
		for(int i=0;i<listeCoup.size();i++) {
			if(listeCoup.get(i).getPosX() < 0 || listeCoup.get(i).getPosY() < 0 || listeCoup.get(i).getPosX() > plateau.getHauteur() || listeCoup.get(i).getPosY() > plateau.getLargeur()) {
				listeCoup.remove(i);
				i--;
			}
		}
		
		
		//Supprime tous les coups avec une pièce entre le cavalier et sa destination
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
		
		//affiche pour test
		for(int i=0; i<listeCoup.size();i++) {
			System.out.println("[" + listeCoup.get(i).posX + "," + listeCoup.get(i).posY + "]");
		}
		System.out.println("------------------");
		
		return listeCoup;
	}

	public void AjoutCoups() {
		ListCoup.add(new Coup(this.getPositionX() + (2 * Cardinal.NordEst.getPosX()), this.getPositionY() + Cardinal.NordEst.getPosY()));
		ListCoup.add(new Coup(this.getPositionX() + (2 * Cardinal.NordOuest.getPosX()), this.getPositionY() + Cardinal.NordOuest.getPosY()));
		ListCoup.add(new Coup(this.getPositionX() + (2 * Cardinal.SudEst.getPosX()), this.getPositionY() + Cardinal.SudEst.getPosY()));
		ListCoup.add(new Coup(this.getPositionX() + (2 * Cardinal.SudOuest.getPosX()), this.getPositionY() + Cardinal.SudOuest.getPosY()));
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.NordEst.getPosX(), this.getPositionY() + (2 * Cardinal.NordEst.getPosY())));
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.NordOuest.getPosX(), this.getPositionY() + (2 * Cardinal.NordOuest.getPosY())));
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.SudEst.getPosX(), this.getPositionY() + (2 * Cardinal.SudEst.getPosY())));
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.SudOuest.getPosX(), this.getPositionY() + (2 * Cardinal.SudOuest.getPosY())));
	}	
}
