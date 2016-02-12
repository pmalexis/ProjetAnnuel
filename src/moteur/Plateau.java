package moteur;

import java.util.LinkedList;
import java.util.List;

public class Plateau {

	private final int LARGEUR = 9;
	private final int HAUTEUR  = 10;
	
	Pieces plateau[][];
	
	Couleur joueurActuel;
	
	/*
	 * Constructeur
	 */
	public Plateau(){
		
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
									{ "  ", "  ", "  ", "  ", "RN", "PR", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "BN", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "BR", "EN", "  ", "PR", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "BR", "  ", "  ", "  ", "  " },
									{ "  ", "  ", "  ", "  ", "RR", "  ", "  ", "  ", "  " },
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
		
		List<Coup> listCoupPossible = this.getListCoupPossible(i, j);
		
		for(int n=0;n<listCoupPossible.size();n++)
			if(listCoupPossible.get(n).getPosX() == iF && listCoupPossible.get(n).getPosY() == jF)
				bool = true;

		if(bool) {
			this.plateau[iF][jF] = this.plateau[i][j];
			this.plateau[i][j]   = new SansPiece  (i, j, Type.SansPiece,  Couleur.Aucune);
			
			this.plateau[iF][jF].setPositionX(iF);
			this.plateau[iF][jF].setPositionY(jF);
		}
		
		if(bool) this.joueurSuivant();
		
		return bool;
	}
	
	public boolean estEchec () {
		
		int c = 0;
		int l = 0;
		boolean res = false;
		
		while (this.getCase(c, l).getType() != Type.Roi) {
			l++;
			if (l >= this.LARGEUR) {
				c++;
				l = 0;
			}
		}
		
		int cpt = 0;
		for (int i = c+1; i < this.HAUTEUR; i++) {
			if (this.getCase(i, l).getType() == Type.Roi) break;
			if (this.getCase(i, l).getType() != Type.SansPiece) cpt++;
		}
		
		if (cpt == 0) res = true;
		
		return res;
	}
	
	public List<Coup> estEchec (Pieces p, List<Coup> listeCoup) {
		
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
			
			int c = 0;
			int l = 0;
			int cpt = 2;
			boolean res = false;
			
			while (this.getCase(c, l).getType() != Type.Roi) {
				l++;
				if (l >= this.LARGEUR) {
					c++;
					l = 0;
				}
			}
			
			if (c < 2) {
				cpt = 0;
			}
			for (int i = c+1; i < this.HAUTEUR; i++) {
				if (this.getCase(i, l).getType() != Type.SansPiece) cpt++;
				if (this.getCase(i, l).getType() == Type.Roi) break;
			}
			
			if (cpt-1 == 0) res = true;
			
			this.plateau[listeCoup.get(k).getPosX()][listeCoup.get(k).getPosY()] = save;
			
			if (p.getCouleur() == Couleur.Noir && echecN) {
				listeCoup.remove(k);
			}
			if (p.getCouleur() == Couleur.Rouge && echecR) {
				listeCoup.remove(k);
			}
			if (res) {
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
}
