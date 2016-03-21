package moteur;

public class Main {
	
	public static void main(String[] args) {
		
		Plateau p = new Plateau(false, false);
		
		p.jouer(3, 5, 2, 5);
		System.out.println(p);
		
		/*p.jouer(8, 1, 1, 1);
		eval = new Evaluateur(p);
		System.out.println("plateau : " + eval.valeurPlateau());
		System.out.println("position : " + eval.valeurPosition());*/
		//System.out.println(p.jouerIA());
		/*System.out.print(p);
		System.out.println("----------------------------------------------");
		
		List<Plateau> poss = p.next();
		for (int i = 0; i < poss.size(); i++) {
			System.out.println(poss.get(i).toString());
		}*/
	}
}
