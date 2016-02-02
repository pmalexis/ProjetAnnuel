package moteur;

import java.util.List;

public class Elephant extends Pieces {
	
	public Elephant(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "E");
		
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
		ListCoup.add(new Coup(this.getPositionX() + (Cardinal.NordEst.getPosX() * 2), this.getPositionY() + (Cardinal.NordEst.getPosY() * 2)));
		ListCoup.add(new Coup(this.getPositionX() + (Cardinal.SudEst.getPosX() * 2), this.getPositionY() + (Cardinal.SudEst.getPosY() * 2)));
		ListCoup.add(new Coup(this.getPositionX() + (Cardinal.NordOuest.getPosX() * 2), this.getPositionY() + (Cardinal.NordOuest.getPosY() * 2)));
		ListCoup.add(new Coup(this.getPositionX() + (Cardinal.SudOuest.getPosX() * 2), this.getPositionY() + (Cardinal.SudOuest.getPosY() * 2)));
	}
	
	private List<Coup> PieceAuMilieu(List<Coup> ListeCoup, Plateau plateau){
		
		/*for(int i = 0; i < ListeCoup.size(); i++){
			if(plateau.LireCase(ListeCoup.get(i).getPositionX() + ListeCoup.get(i).getCardinal().Opposee().getPosX(), ListeCoup.get(i).getPositionY() + ListeCoup.get(i).getCardinal().Opposee().getPosY()).getType() != Type.SansPiece){
				ListeCoup.remove(i);
			}
		}*/
		
		return ListeCoup;
	}
}
