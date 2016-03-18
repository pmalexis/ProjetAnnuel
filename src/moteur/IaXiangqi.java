package moteur;

import java.util.List;

public class IaXiangqi {

	Couleur couleur;
	Plateau plateau;
	
	public IaXiangqi(Couleur c) {
		this.couleur = c;
	}
	
	public int[] jouer(Plateau p) {
		this.plateau = p;
		
		this.min(2);
		
		return null;
	}
	
	//coup de l'ia actuel
	private int min(int p) {
		
		if(!this.plateau.estEchec() || p > 0) {		
			
			//On créer une save du tableau de pieces
			Pieces[][] tabPsave = new Pieces[this.plateau.getPlateau().length][this.plateau.getPlateau()[0].length];
			for(int i=0;i<tabPsave.length;i++)
				for(int j=0;j<tabPsave[0].length;j++)
					tabPsave[i][j] = this.plateau.getPlateau()[i][j];
			
			//On parcours tout le tableau pour trouver les pieces
			for(int i=0;i<this.plateau.getPlateau().length;i++)
				for(int j=0;j<this.plateau.getPlateau()[0].length;j++)
					if(this.plateau.getPlateau()[i][j].getCouleur() == this.couleur) {
						List<Coup> lc = this.plateau.getListCoupPossible(i, j);
						Pieces pSave;
						if(lc != null) { //System.out.println(lc);
							for(int k=0;k<lc.size();k++) {
								
								System.out.println("MAX [" + i + "][" + j + "] " + k + " " + this.plateau.getPlateau()[i][j].getType());
								System.out.println(lc); //liste ce met à jour toute seule ?! Oo
								
								//changement pos
								int iF = lc.get(k).getPosX();
								int jF = lc.get(k).getPosY();
								pSave = this.plateau.getPlateau()[iF][jF];
								this.plateau.jouer(i, j, iF, jF);
								
								this.max(p-1); 
								//this.afficher(this.plateau.getPlateau());
								
								//reset pos
								this.deJouer(i, j, iF, jF, pSave);
							}
							this.plateau.plateau = tabPsave;
						}
					}
			this.plateau.plateau = tabPsave;
		}
		else this.heuristique(p);			
		
		return 0;
	}

	//coup de l'ia adverse
	private int max(int p) {

		if(!this.plateau.estEchec() && p > 0) {		
			
			//On créer une save du tableau de pieces
			Pieces[][] tabPsave = new Pieces[this.plateau.getPlateau().length][this.plateau.getPlateau()[0].length];
			for(int i=0;i<tabPsave.length;i++)
				for(int j=0;j<tabPsave[0].length;j++)
					tabPsave[i][j] = this.plateau.getPlateau()[i][j];
			
			//On parcours tout le tableau pour trouver les pieces
			for(int i=0;i<this.plateau.getPlateau().length;i++)
				for(int j=0;j<this.plateau.getPlateau()[0].length;j++)
					if(this.plateau.getPlateau()[i][j].getCouleur() != this.couleur) {
						List<Coup> lc = this.plateau.getListCoupPossible(i, j);
						Pieces pSave;
						
						if(lc != null) { //System.out.println(lc);
							for(int k=0;k<lc.size();k++) {
								
								System.out.println(" -> MIN [" + i + "][" + j + "] " + k + " " + this.plateau.getPlateau()[i][j].getType());

								//changement pos
								pSave = this.plateau.getPlateau()[lc.get(k).getPosX()][lc.get(k).getPosY()];
								this.plateau.jouer(i, j, lc.get(k).getPosX(), lc.get(k).getPosY());
								
								//this.min(tabP, p-1);
								//this.afficher(this.plateau.getPlateau());
								
								//reset pos
								this.deJouer(i, j, lc.get(k).getPosX(), lc.get(k).getPosY(), pSave);
							}
							this.plateau.plateau = tabPsave;
						}
					}
			this.plateau.plateau = tabPsave;
		}
		else this.heuristique(p);			
		
		return 0;
	}
	
	private int heuristique(int p) {
		System.out.println(p);
		return 0;
	}
	
	private void deJouer(int i, int j, int iF, int jF, Pieces pSave) {
		if(i==2 && j==4) {
			System.out.println(i + " : " + j + " : " + this.plateau.getPlateau()[iF][jF].getType());
		}
		
		this.plateau.getPlateau()[iF][jF].setPositionX(i);
		this.plateau.getPlateau()[iF][jF].setPositionY(j);
		
		this.plateau.getPlateau()[i][j]   = this.plateau.getPlateau()[iF][jF];
		this.plateau.getPlateau()[iF][jF] = pSave;
	}
	
	private void afficher(Pieces[][] tabP) {
		String str = "";
		
		for(int i=0;i<tabP.length;i++) {
			str += "| ";
			for(int j=0;j<tabP[0].length;j++) {
				if(tabP[i][j].getType() == Type.SansPiece) str += "   | ";
				else str += tabP[i][j].getName() + " | ";
			}
			str += "\n";
		}
		
		System.out.println(str);
	}
}
