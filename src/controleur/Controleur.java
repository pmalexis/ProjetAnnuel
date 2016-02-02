package controleur;

import java.util.List;

import moteur.Coup;
import moteur.Pieces;
import moteur.Plateau;
import moteur.Type;

public class Controleur {

	Plateau plateau;
	
	public Controleur() {
		plateau = new Plateau();
	}
	
	public String[][] getTabChess() {
		Pieces[][] tabPion  = this.plateau.getPlateau();
		String[][] tabTempo = new String[tabPion.length][tabPion[0].length];
		
		for(int i=0;i<tabTempo.length;i++)
			for(int j=0;j<tabTempo[0].length;j++)
				tabTempo[i][j] = tabPion[i][j].getName();
		
		return tabTempo;
	}

	public Type getType(int[] tab) {
		return this.plateau.getCase(tab[0], tab[1]).getType();
	}

	public List<Coup> getListCoupPossible(int i, int j) {
		return this.plateau.getListCoupPossible(i,j);
	}
}
