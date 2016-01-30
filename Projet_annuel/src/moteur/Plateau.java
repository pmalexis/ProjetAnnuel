package moteur;

public class Plateau {
	
	protected int Longueur;
	protected int Largeur;
	Pieces Plateau[][] = new Pieces[10][9];
	
	public Plateau(){
		
		this.Largeur = 10;
		this.Longueur = 9;
	}
	
	public Pieces[][] InitialisationPlateau(){
		
		//ListePiece.add(new Tour(1, 1, Type.TourB, null));
		//ListePiece.add(new Roi(4, 0, Type.RoiB));
		//ListePiece.add(new Roi(3, 7, Type.RoiN, Couleur.Noir));
		//ListePiece.add(new Garde(3, 9, Type.GardeN, Couleur.Noir));
		//ListePiece.add(new Roi(3, 7, Type.RoiN, Couleur.Noir));
		//ListePiece.add(new Elephant(2, 5, Type.ElephantN, Couleur.Noir, Cardinal.NordEst));
		//ListePiece.add(new Pion(0, 7, Type.PionN, Couleur.Noir, false, Cardinal.Nord));
		//ListePiece.add(new Pion(4, 7, Type.PionN, Couleur.Noir, false));
		
		/*for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				Plateau[i][j] = new SansPiece(i,j);
			}
		}
		*/
		return Plateau;
	}
}
