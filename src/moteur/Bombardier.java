package moteur;

import java.util.List;

public class Bombardier extends Pieces {
	
	public Bombardier (int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "B");
		
		AjoutCoups();
	}
	
	public List<Coup> getListeCoupPossible(List<Coup> listeCoup, Plateau plateau) {
		
		int a = 0;
		int a1 = 0;
		if (this.getPositionX() < 9) {
			for(int i=0;i<getListCoup().size();i++)
				if(plateau.getCase(listeCoup.get(a).getPosX(), listeCoup.get(a).getPosY()).getType() == Type.SansPiece)
					if (this.getPositionX() < listeCoup.get(a).getPosX())
						a = i;
			for(a1=a+1;a1<getListCoup().size();a1++)
				if(plateau.getCase(listeCoup.get(a1).getPosX(), listeCoup.get(a1).getPosY()).getType() != Type.SansPiece || this.getPositionX() >= listeCoup.get(a1).getPosX())
					break;
			
			if (this.getPositionX() + a1 + 1< plateau.getHauteur())
				if (plateau.getCase(this.getPositionX()+a1+1, this.getPositionY()).getCouleur() != this.getCouleur())
					listeCoup.add(new Coup(this.getPositionX()+a1+1,this.getPositionY()));
			
			for (int i = 8 - this.getPositionX(); i >= a; i--) {
				listeCoup.remove(i);
			}
		}
		
		int b = a;
		int b1 = a;
		if (this.getPositionX() > 0) {
			for(int i=a;i<getListCoup().size();i++)
				if(plateau.getCase(listeCoup.get(b).getPosX(), listeCoup.get(b).getPosY()).getType() == Type.SansPiece)
					if (this.getPositionX() != listeCoup.get(b).getPosX())
						b = i;
			for(b1=b+1;b1<getListCoup().size();b1++)
				if(plateau.getCase(listeCoup.get(b1).getPosX(), listeCoup.get(b1).getPosY()).getType() != Type.SansPiece)
					break;
			if (a1 + b1 < plateau.getHauteur() && (this.getPositionX()+a-1-b1) >= 0)
				if (plateau.getCase(this.getPositionX()+a-1-b1, this.getPositionY()).getCouleur() != this.getCouleur())
					listeCoup.add(new Coup(this.getPositionX()+a-1-b1,this.getPositionY()));

			for (int i = this.getPositionX() + a - 1; i >= b; i--) {
				listeCoup.remove(i);
			}
		}
		
		int c = b;
		int c1 = b;
		if (this.getPositionY() < 8) {
			for(int i=b;i<getListCoup().size();i++)
				if(plateau.getCase(listeCoup.get(c).getPosX(), listeCoup.get(c).getPosY()).getType() == Type.SansPiece)
					if (this.getPositionY() < listeCoup.get(c).getPosY())
						c = i + 1;
			for(c1=c+1;c1<getListCoup().size();c1++)
				if(plateau.getCase(listeCoup.get(c1).getPosX(), listeCoup.get(c1).getPosY()).getType() != Type.SansPiece)
					break;
			
			if (this.getPositionY() + c1 +1 -b < plateau.getLargeur())
				if (plateau.getCase(this.getPositionX(), this.getPositionY()-b+1+c1).getCouleur() != this.getCouleur())
					listeCoup.add(new Coup(this.getPositionX(),this.getPositionY()-b+1+c1));
			
			for (int i = b + 7 - this.getPositionY(); i >= c; i--) {
				listeCoup.remove(i);
			}
		}
		
		int d = c;
		int d1 = c;
		if (this.getPositionY() > 0) {
			for(d=c;d<getListCoup().size();d++)
				if(plateau.getCase(listeCoup.get(d).getPosX(), listeCoup.get(d).getPosY()).getType() != Type.SansPiece)
					break;
			for(d1=d+1;d1<getListCoup().size();d1++)
				if(plateau.getCase(listeCoup.get(d1).getPosX(), listeCoup.get(d1).getPosY()).getType() != Type.SansPiece)
					break;

			if (d1 <= listeCoup.size() && this.getPositionY()-d1+c-1 >= 0)
				if (plateau.getCase(this.getPositionX(), this.getPositionY()-d1+c-1).getCouleur() != this.getCouleur())
					listeCoup.add(new Coup(this.getPositionX(),this.getPositionY()-d1+c-1));
			
			for (int i = c + this.getPositionY() - 1; i >= d; i--) {
				listeCoup.remove(i);
			}
		}
		return listeCoup;
	}

	public void AjoutCoups() {
		List<Coup> liste = this.getListCoup();
		liste.clear();
		
		for(int i = this.getPositionX() + 1; i < 10; i++){
			liste.add(new Coup(i, this.getPositionY()));
		}
		
		for(int i = this.getPositionX() - 1; i >= 0; i--){
			liste.add(new Coup(i, this.getPositionY()));
		}
		
		for(int i = this.getPositionY() + 1; i < 9; i++){
			liste.add(new Coup(this.getPositionX(), i));
		}
		
		for(int i = this.getPositionY() - 1; i >= 0; i--){
			liste.add(new Coup(this.getPositionX(), i));
		}
	}
}
