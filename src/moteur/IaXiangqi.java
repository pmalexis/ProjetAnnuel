package moteur;

import java.util.ArrayList;
import java.util.List;

public class IaXiangqi {

	Couleur couleur;
	Plateau plateau;
	Coup nextCoup;
	
	public IaXiangqi(Couleur c) {
		this.couleur = c;
	}
	
	public Plateau jouer(Plateau p) {
		this.plateau = p;
		ArrayList<Plateau> possible = p.next();
		long[] index = IaXiangqi.alphaBeta(this.plateau, 2, 0, 0);
		
		return possible.get((int)index[1]);
	}
	
	public static long[] alphaBeta(Plateau p, int prof, long a, long b) {
		if(prof <= 0) {
			long[] valeurs = {heuristique(p), 0};
			return valeurs;
		}
		else {
			long meilleur = -5000; //à définir
			ArrayList<Plateau> possible = p.next();
			int i;
			for(i=0;i<possible.size();i++) {
				long[] val = IaXiangqi.alphaBeta(possible.get(i), prof-1, -a, -b);
				if(-val[0] > meilleur) {
					meilleur = -val[0];
					if(meilleur > a) {
						a = meilleur;
						if(a >= b) {
							long[] valeurs = {meilleur, i};
							return valeurs;
						}
					}
				}
			}
			long[] valeurs = {meilleur, i};
			return valeurs;
		}
	}
	
	/*public static Plateau MinMax (int profondeur, Plateau init, long max, long min) {
		long tmp;
		Plateau coup = new Plateau(true, true);
		max = -200;
		ArrayList<Plateau> possible = init.next();
		for (int i = 0; i < possible.size(); i++) {
			//System.out.println(possible.get(i));
			tmp = Min(profondeur-1, possible.get(i), max, min);
			if (tmp > max) {
				max = tmp;
				coup = Plateau.copyPlateau(possible.get(i));
			}
		}					
		return coup;
	}
	
	public static long Min (int profondeur, Plateau plat, long max, long min) {
		if (profondeur == 0) {
			//System.out.println(dij.Evaluation(gb.joueur1, gb.joueur2));
			return heuristique(plat);
		} else {
			min = 200;
			ArrayList<Plateau> possible = plat.next();
			for (int i = 0; i < possible.size(); i++) {
				System.out.println(possible.get(i));
				min = Math.min(min, Max(profondeur-1, possible.get(i), max, min));
			}
		}
		return min;
	}
	
	public static long Max (int profondeur, Plateau plat, long max, long min) {
		if (profondeur == 0) {
			//System.out.println(dij.Evaluation(gb.joueur1, gb.joueur2));
			return heuristique(plat);
		} else {
			max = -200;
			ArrayList<Plateau> possible = plat.next();
			for (int i = 0; i < possible.size(); i++) {
				//System.out.println(possible.get(i));
				max = Math.max(max, Min(profondeur-1, possible.get(i), max, min));
			}
		}
		return max;
	}*/
	
	private static long heuristique(Plateau p) {
		Evaluateur eval = new Evaluateur(p);
		return eval.evaluation();
	}
	
	/*private void afficher(Pieces[][] tabP) {
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
	}*/
}
