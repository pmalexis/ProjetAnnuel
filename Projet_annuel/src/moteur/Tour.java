package moteur;

import java.util.List;

public class Tour extends Pieces {
	
	public Tour (int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "T");
		
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
		for(int i = this.getPositionX() + 1; i < 9; i++){
			ListCoup.add(new Coup(i, this.getPositionY()));
		}
		
		for(int i = this.getPositionX() - 1; i >= 0; i--){
			ListCoup.add(new Coup(i, this.getPositionY()));
		}
		
		for(int i = this.getPositionY() + 1; i < 10; i++){
			ListCoup.add(new Coup(this.getPositionX(), i));
		}
		
		for(int i = this.getPositionY() - 1; i >= 0; i--){
			ListCoup.add(new Coup(this.getPositionX(), i));
		}
	}
}
