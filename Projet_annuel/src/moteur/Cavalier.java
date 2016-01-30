package moteur;

import java.util.List;

public class Cavalier extends Pieces {
	
	public Cavalier(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "C");
		
		AjoutCoups();
	}

	public List<Coup> ListeCoupPossible(List<Coup> ListeCoup, Plateau plateau) {
		
		/*for(int i = 0; i < ListeCoup.size(); i++){
			if(plateau.LireCase(ListeCoup.get(i).getPositionX(), ListeCoup.get(i).getPositionY()).getType() != Type.SansPiece){
				for (int j = i+1; j < ListeCoup.size(); i++){
					
				}
				/*if(plateau.LireCase(ListeCoup.get(i).getPositionX(), ListeCoup.get(i).getPositionY()).getCouleur() == ListeCoup.get(i).getCouleur()){
					
				}//
			}
		}*/	
		return ListeCoup;
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
	
	private List<Coup> PieceAuMilieu(List<Coup> ListeCoup, Plateau plateau){
		
		/*for(int i = 0; i < ListeCoup.size(); i++){
			if(plateau.getCase(ListeCoup.get(i).getPosX() + ListeCoup.get(i).getCardinal().Opposee().getPosX(), ListeCoup.get(i).getPositionY() + ListeCoup.get(i).getCardinal().Opposee().getPosY()).getType() != Type.SansPiece){
				ListeCoup.remove(i);
			}
		}*/
		
		return ListeCoup;
	}
}
