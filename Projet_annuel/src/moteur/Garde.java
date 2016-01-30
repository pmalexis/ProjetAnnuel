package moteur;

import java.util.LinkedList;
import java.util.List;

public class Garde extends Pieces {
	
	public Garde (int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "G");
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

	public List<Coup> ListeCoup() {
		LinkedList<Coup> ListeCoup = new LinkedList<Coup>();
		
		ListeCoup.add(new Coup(this.getPositionX() + Cardinal.NordEst.getPosX(), this.getPositionY() + Cardinal.NordEst.getPosY()));
		ListeCoup.add(new Coup(this.getPositionX() + Cardinal.SudEst.getPosX(), this.getPositionY() + Cardinal.SudEst.getPosY()));
		ListeCoup.add(new Coup(this.getPositionX() + Cardinal.NordOuest.getPosX(), this.getPositionY() + Cardinal.NordOuest.getPosY()));
		ListeCoup.add(new Coup(this.getPositionX() + Cardinal.SudOuest.getPosX(), this.getPositionY() + Cardinal.SudOuest.getPosY()));
		return ListeCoup;
	}
}
