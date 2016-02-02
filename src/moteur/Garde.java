package moteur;

import java.util.List;

public class Garde extends Pieces {
	
	public Garde (int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "G");
		
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
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.NordEst.getPosX(), this.getPositionY() + Cardinal.NordEst.getPosY()));
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.SudEst.getPosX(), this.getPositionY() + Cardinal.SudEst.getPosY()));
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.NordOuest.getPosX(), this.getPositionY() + Cardinal.NordOuest.getPosY()));
		ListCoup.add(new Coup(this.getPositionX() + Cardinal.SudOuest.getPosX(), this.getPositionY() + Cardinal.SudOuest.getPosY()));
	}
}
