package Controleur;

import java.util.List;

import moteur.Pieces;
import moteur.Plateau;

public class Controleur {

	Plateau plateau;
	
	public Controleur() {
		plateau = new Plateau();
		List<Pieces> ListePiece = plateau.InitialisationPlateau();
		plateau.RemplirPlateau();
		List<Pieces> ListeCoup = null;
		
		ListeCoup = ListePiece.get(0).GestionLCP(ListeCoup, plateau);
	}
	
	public String[][] getTabChess() {
		Pieces[][] tabPion = this.plateau.getPlateau();
		String[][] tabTempo = new String[tabPion.length][tabPion[0].length];
		
		for(int i=0;i<tabTempo.length;i++)
			for(int j=0;j<tabTempo[0].length;j++)
				tabTempo[i][j] = tabPion[i][j].getName();
		
		return tabTempo;
	}
}
