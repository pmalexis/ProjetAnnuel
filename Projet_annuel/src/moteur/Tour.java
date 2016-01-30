package moteur;

import java.util.LinkedList;
import java.util.List;

public class Tour extends Pieces {
	
	public Tour (int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "T");
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
		
		for(int i = this.getPositionX() + 1; i < 9; i++){
			ListeCoup.add(new Coup(i, this.getPositionY()));
		}
		
		for(int i = this.getPositionX() - 1; i >= 0; i--){
			ListeCoup.add(new Coup(i, this.getPositionY()));
		}
		
		for(int i = this.getPositionY() + 1; i < 10; i++){
			ListeCoup.add(new Coup(this.getPositionX(), i));
		}
		
		for(int i = this.getPositionY() - 1; i >= 0; i--){
			ListeCoup.add(new Coup(this.getPositionX(), i));
		}
		
		return ListeCoup;
	}
}
