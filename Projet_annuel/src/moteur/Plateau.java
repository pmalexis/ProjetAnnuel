package moteur;

public class Plateau {

	private final int LARGEUR = 9;
	private final int HAUTEUR  = 10;
	
	Pieces plateau[][];
	
	/*
	 * Constructeur
	 */
	public Plateau(){
		
		this.plateau = new Pieces[this.LARGEUR][this.HAUTEUR];
 
		this.initialisationPlateau();
	}
	
	/*
	 * Retourne la case du plateau celon posX et posY
	 */
	public Pieces getCase(int posX, int posY){
		return this.plateau[posY][posX];
	}
	
	/*
	 * Retourne le plateau
	 */
	public Pieces[][] getPlateau() {
		return this.plateau;
	}
	
	/*
	 * Initialise et remplie le plateau
	 */
	public void initialisationPlateau(){
		
		String[][] plateauTempo = { { "TN", "CN", "EN", "GN", "RN", "GN", "EN", "CN", "TN" }, 
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "BN", "  ", "  ", "  ", "  ", "  ", "BN", "  " },
									{ "PN", "  ", "PN", "  ", "PN", "  ", "PN", "  ", "PN" },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "PR", "  ", "PR", "  ", "PR", "  ", "PR", "  ", "PR" },
									{ "  ", "BR", "  ", "  ", "  ", "  ", "  ", "BR", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "TR", "CR", "ER", "GR", "RR", "GR", "ER", "CR", "TR" } };
		
		/*for(int i = 0; i < this.LARGEUR; i++)
			for(int j = 0; j < this.HAUTEUR; j++)
				this.plateau[i][j] = new SansPiece(i, j, Type.SansPiece, Couleur.Aucune);*/
			
		for(int i = 0; i < plateauTempo.length-1; i++)
			for(int j = 0; j < plateauTempo[0].length; j++) {
				Pieces p;
				Couleur c;
				
				if(plateauTempo[i][j].charAt(1) == 'N') c = Couleur.Noir;
				else c = Couleur.Rouge;
				
				switch(plateauTempo[i][j].charAt(0)) {
					case 'T' : p = new Tour       (i, j, Type.Tour      , c);
					case 'C' : p = new Cavalier   (i, j, Type.Cavalier  , c);
					case 'E' : p = new Elephant   (i, j, Type.Elephant  , c);
					case 'G' : p = new Garde      (i, j, Type.Garde     , c);
					case 'R' : p = new Roi        (i, j, Type.Roi       , c);
					case 'B' : p = new Bombardier (i, j, Type.Bombardier, c);
					case 'P' : p = new Pion       (i, j, Type.Pion      , c);
					default : p = new SansPiece   (i, j, Type.SansPiece,  c);
				}
				
				this.plateau[i][j] = p;
			}
				
	}
	
	
	/*
	 * Retounre un string qui est le plateau actuel du xiangqi
	 */
	public String toString(){
		
		String str = "";
		
		for(int i = 0; i < this.HAUTEUR-1; i++){
			str += "| ";
			for(int j = 0; j < this.LARGEUR; j++){
				if(this.plateau[i][j] != null)
					str += this.plateau[i][j].getType().getRepresentation() + "" + this.plateau[i][j].getCouleur().getCouleur() + " |";
				else str += " .. |";
			}
			str += "\n";
		}
		return str;
	}	
}
