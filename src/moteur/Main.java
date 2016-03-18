package moteur;

import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		Plateau p = new Plateau(true, true);
		
		System.out.println(p);
		
		Pieces piece = p.getCase(7, 4);
		
		System.out.println(piece.getType() + " " + piece.getCouleur());
		
		List<Coup> LC = piece.getListCoup();
		List<Coup> LCP = piece.getListeCoupPossible(LC, p);
		
		System.out.println(LCP.size());
		for (int i = 0; i < LCP.size(); i++) {
			System.out.println(LCP.get(i));
		}
		
		/*piece = p.getCase(7, 2);
		
		System.out.println(piece.getType() + " " + piece.getCouleur());
		
		LC = piece.getListCoup();
		LCP = piece.ListeCoupPossible(LC, p);
		
		System.out.println(LCP.size());
		for (int i = 0; i < LCP.size(); i++) {
			System.out.println(LCP.get(i));
		}*/
	}
}
