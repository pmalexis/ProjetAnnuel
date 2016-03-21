package moteur;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Plateau {

	private final static int LARGEUR = 9;
	private final static int HAUTEUR  = 10;
	
	public Pieces plateau[][];
	
	public Couleur joueurActuel;
	
	public boolean joueurUn_IA, joueurDeux_IA;
	
	/*
	 * Constructeur
	 */
	public Plateau(boolean jUnIa, boolean jDeuxIa){
		
		this.joueurUn_IA   = jUnIa;
		this.joueurDeux_IA = jDeuxIa;
		
		this.plateau = new Pieces[this.HAUTEUR][this.LARGEUR];
 
		this.joueurActuel = Couleur.Rouge;
		
		this.initialisationPlateau();
	}
	
	/*
	 * Retourne la case du plateau celon posX et posY
	 */
	public Pieces getCase(int posX, int posY){
		return this.plateau[posX][posY];
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
		
		/*
		String[][] plateauTempo = { { "TN", "CN", "EN", "GN", "RN", "GN", "EN", "CN", "TN" }, 
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "BN", "  ", "  ", "  ", "  ", "  ", "BN", "  " },
									{ "PN", "  ", "PN", "  ", "PN", "  ", "PN", "  ", "PN" },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "PR", "  ", "PR", "  ", "PR", "  ", "PR", "  ", "PR" },
									{ "  ", "BR", "  ", "  ", "  ", "  ", "  ", "BR", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "TR", "CR", "ER", "GR", "RR", "GR", "ER", "CR", "TR" } };*/
		
		String[][] plateauTempo = { { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " }, 
									{ "  ", "  ", "  ", "  ", "  ", "RN", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "PN", "PR", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "RR", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " } };
			
		for(int i = 0; i < plateauTempo.length; i++)
			for(int j = 0; j < plateauTempo[0].length; j++) {
				Pieces p;
				Couleur c;
				
				if(plateauTempo[i][j].charAt(1) == 'N') c = Couleur.Noir;
				else c = Couleur.Rouge;
				
				switch(plateauTempo[i][j].charAt(0)) {
					case 'T' : p = new Tour       (i, j, Type.Tour      , c); break;
					case 'C' : p = new Cavalier   (i, j, Type.Cavalier  , c); break;
					case 'E' : p = new Elephant   (i, j, Type.Elephant  , c); break;
					case 'G' : p = new Garde      (i, j, Type.Garde     , c); break;
					case 'R' : p = new Roi        (i, j, Type.Roi       , c); break;
					case 'B' : p = new Bombardier (i, j, Type.Bombardier, c); break;
					case 'P' : p = new Pion       (i, j, Type.Pion      , c); break;
					default  : p = new SansPiece  (i, j, Type.SansPiece,  Couleur.Aucune);
				}
				
				this.plateau[i][j] = p;
			}
	}
	
	public int getHauteur() {
		return this.HAUTEUR;
	}	
	
	public int getLargeur() {
		return this.LARGEUR;
	}	
	
	public Couleur getJoueurAct() {
		return this.joueurActuel;
	}
	
	public boolean getJoueurUn_IA() {
		return this.joueurUn_IA;
	}
	
	public boolean getJoueurDeux_IA() {
		return this.joueurDeux_IA;
	}
	
	public void joueurSuivant() {
		if(this.joueurActuel == Couleur.Rouge) this.joueurActuel = Couleur.Noir;
		else this.joueurActuel = Couleur.Rouge;
	}
	
	/*
	 * Retourne la liste de coup possible de la pièce en position i,j
	 */
	public List<Coup> getListCoupPossible(int i, int j) {
		this.plateau[i][j].AjoutCoups();
		return this.plateau[i][j].getListeCoupPossible(this.plateau[i][j].getListCoup(), this);
	}
	
	/*
	 * Retourne un boolean celon si le coup à pu être jouer ou non
	 */
	public boolean jouer(int i, int j, int iF, int jF) {
		boolean bool = false;

		System.out.println("JOUER");
		List<Coup> listCoupPossible = this.getListCoupPossible(i, j);
		//System.out.println(listCoupPossible.size());
		
		for(int n=0;n<listCoupPossible.size();n++) {
			if(listCoupPossible.get(n).getPosX() == iF && listCoupPossible.get(n).getPosY() == jF)
				bool = true;
		}

		if(bool) {
			this.plateau[iF][jF] = this.plateau[i][j];
			this.plateau[i][j]   = new SansPiece  (i, j, Type.SansPiece,  Couleur.Aucune);
			
			this.plateau[iF][jF].setPositionX(iF);
			this.plateau[iF][jF].setPositionY(jF);
		}
		
		if(bool) this.joueurSuivant();
		
		return bool;
	}
	
	public void jouerIA() {
		IaXiangqi ia = new IaXiangqi(this.getJoueurAct());
		this.plateau = Plateau.copyPieces(ia.jouer(this).plateau);
		this.joueurSuivant();
	}
	
	public boolean estEchec() {
		return estEchec(Couleur.Noir) || estEchec(Couleur.Rouge);
	}
	
	public boolean estEchec (Couleur couleur) {
		
		Coup c = null;
		for (int i = 0; i < this.HAUTEUR; i++) {
			for (int j = 0; j < this.LARGEUR; j++) {
				Pieces p = this.plateau[i][j];
				if (p.getType() == Type.Roi && p.getCouleur() == couleur) {
					c = new Coup(i,j);
				}
			}
		}
		
		for (int i = 0; i < this.HAUTEUR; i++) {
			for (int j = 0; j < this.LARGEUR; j++) {
				Pieces p = this.plateau[i][j];
				if (p.getCouleur().oppose(couleur)) {
					//System.out.println(p.getType() + " :");
					List<Coup> liste = this.getListCoupPossible(i, j);
					for (int k = 0; k < liste.size(); k++) {
						//System.out.println("\t" + liste.get(k));
						if (liste.get(k).equals(c)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public List<Coup> estEchec (Pieces p, List<Coup> listeCoup) {
		
		System.out.println(p.getType() + " " + p.getCouleur() + " :\n" + this);
		
		for (int k = listeCoup.size()-1; k >= 0; k--) {
			
			Pieces save = this.plateau[listeCoup.get(k).getPosX()][listeCoup.get(k).getPosY()];

			this.plateau[listeCoup.get(k).getPosX()][listeCoup.get(k).getPosY()] = p;
			this.plateau[p.getPositionX()][p.getPositionY()] = new SansPiece(p.getPositionX(), p.getPositionY(), Type.SansPiece, Couleur.Aucune);
			
			LinkedList<Pieces> bombardier = new LinkedList<Pieces>();
			for (int i = 0; i < this.HAUTEUR; i++) {
				for (int j = 0; j < this.LARGEUR; j++) {
					if (this.getCase(i, j).getType() == Type.Bombardier && (i != listeCoup.get(k).getPosX() || j != listeCoup.get(k).getPosY()) ) {
						bombardier.add(this.getCase(i, j));
					}
				}
			}
			Coup[] roi = new Coup[2];
			for (int i = 0; i < this.HAUTEUR; i++) {
				for (int j = 0; j < this.LARGEUR; j++) {
					if (this.getCase(i, j).getName().equals("RR")) {
						roi[0] = new Coup(i,j);
					}
					if (this.getCase(i, j).getName().equals("RN")) {
						roi[1] = new Coup(i,j);
					}
				}
			}
			
			boolean echecN = false;
			boolean echecR = false;
			Bombardier.test = false;
			for (int i = 0; i < bombardier.size(); i++) {
				List<Coup> liste = this.getListCoupPossible(bombardier.get(i).getPositionX(), bombardier.get(i).getPositionY());
				for (int j = 0; j < liste.size(); j++) {
					if (roi[0] != null && roi[0].equals(liste.get(j))) {
						echecR = true;
					}
					if (roi[1] != null && roi[1].equals(liste.get(j))) {
						echecN = true;
					}
				}
			}
			Bombardier.test = true;
			
			int l = 0;
			int c = 0;
			boolean res = false;
			
			/*for (l = 0; l < this.HAUTEUR; l++) {
				for (c = 0; c < this.LARGEUR; c++) {
					if (this.getCase(l, c).getType() != Type.Roi) break;
				}
			}*/
			//System.out.println(this);
			while (this.getCase(l, c).getType() != Type.Roi) {
				c++;
				if (c >= this.LARGEUR) {
					l++;
					c = 0;
				}
			}
			int i;
			for (i = l+1; i < this.HAUTEUR-1; i++) {
				if (this.getCase(i, c).getType() != Type.SansPiece) break;
			}
			
			if (this.getCase(i, c).getType() == Type.Roi) res = true;
			
			this.plateau[listeCoup.get(k).getPosX()][listeCoup.get(k).getPosY()] = save;
			
			if (p.getCouleur() == Couleur.Noir && echecN) {
				System.out.println("noir = " + listeCoup.get(k));
				listeCoup.remove(k);
			}
			if (p.getCouleur() == Couleur.Rouge && echecR) {
				System.out.println("rouge = " + listeCoup.get(k));
				listeCoup.remove(k);
			}
			if (res) {
				System.out.println("autre = " + listeCoup.get(k));
				listeCoup.remove(k);
			}
		}
		this.plateau[p.getPositionX()][p.getPositionY()] = p;
		
		return listeCoup;
	}
	
	/*
	 * Retourne un string qui est le plateau actuel du xiangqi
	 */
	public String toString(){
		
		String str = "";
		
		for(int i = 0; i < this.HAUTEUR; i++){
			str += "|";
			for(int j = 0; j < this.LARGEUR; j++){
				str += " " + this.plateau[i][j].getType().getRepresentation() + "" + this.plateau[i][j].getCouleur().getCouleur() + " |";
			}
			str += "\n";
		}
		return str;
	}
	
	public static Pieces[][] copyPieces(Pieces[][] plat) {
		Pieces[][] p = new Pieces[HAUTEUR][LARGEUR];
		for (int i = 0; i < HAUTEUR; i++) {
			for (int j = 0; j < LARGEUR; j++) {
				p[i][j] = plat[i][j];
			}
		}
		return p;
	}

	public static Plateau copyPlateau(Plateau plat) {
		Plateau p = new Plateau(plat.joueurUn_IA, plat.joueurDeux_IA);
		p.joueurActuel = plat.getJoueurAct();
		for (int i = 0; i < plat.HAUTEUR; i++) {
			for (int j = 0; j < plat.LARGEUR; j++) {
				p.plateau[i][j] = plat.plateau[i][j];
			}
		}
		return p;
	}

	public ArrayList<Plateau> next() {
		ArrayList<Plateau> coupPossibles = new ArrayList<Plateau>();
		for (int i = 0; i < this.HAUTEUR; i++) {
			for (int j = 0; j < this.LARGEUR; j++) {
				Pieces p = this.plateau[i][j];
				if (p.getType() != Type.SansPiece && p.getCouleur() == this.getJoueurAct()) {
					System.out.println("POSSIBLE");
					List<Coup> coupsPiece = p.getListeCoupPossible(p.getListCoup(), this);
					for (int k = 0; k < coupsPiece.size(); k++) {
						Plateau newPlat = copyPlateau(this);
						System.out.println("NEXT : " + coupsPiece.get(k));
						newPlat.jouer(i, j, coupsPiece.get(k).getPosX(), coupsPiece.get(k).getPosY());
						coupPossibles.add(newPlat);
					}
				}
			}
		}
		return coupPossibles;
	}
	
	public List<Integer> indexUnitees (Type type, Couleur couleur) {
		List<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < this.HAUTEUR; i++) {
			for (int j = 0; j < this.LARGEUR; j++) {
				Pieces p = this.plateau[i][j];
				if (p.getType() == type && p.getCouleur() == couleur) {
					index.add(i*9+j);
				}
			}
		}
		return index;
	}
}
