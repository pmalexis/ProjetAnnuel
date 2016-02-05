package moteur;

import java.util.LinkedList;
import java.util.List;

public abstract class Pieces {
	private int PositionX;
	private int PositionY;
	private Type Type;
	private Couleur Couleur;
	private String name;
	private LinkedList<Coup> listCoup = new LinkedList<Coup>();
	
	@SuppressWarnings("static-access")
	public Pieces (int PositionX, int PositionY, Type Type, Couleur Couleur, String name) {
		this.PositionX = PositionX;
		this.PositionY = PositionY;
		this.Type =Type;
		this.Couleur = Couleur;
		
		this.name = name;
		if(name.length() == 1)
			if(this.Couleur == Couleur.Rouge) this.name += 'R';
			else this.name += 'N';
	}

	public Couleur getCouleur() {
		return Couleur;
	}

	public Type getType() {
		return Type;
	}
	
	public int getPositionX() {
		return PositionX;
	}

	public int getPositionY() {
		return PositionY;
	}

	public void setPositionX(int x) {
		PositionX = x;
	}

	public void setPositionY(int y) {
		PositionY = y;
	}
	
	public String getName() {
		return this.name;
	}

	public List<Coup> getListCoup() {
		return this.listCoup;
	}
	
	public abstract void AjoutCoups();
	public abstract List<Coup> getListeCoupPossible(List<Coup> ListeCoup, Plateau plateau);
	
	
	public List<Coup> GestionLCP(List<Coup> ListeCoup, Plateau plateau){
		ListeCoup = this.listCoup;
		ListeCoup = this.getListeCoupPossible(ListeCoup, plateau);
		return ListeCoup;
	}
	
	public List<Pieces> GestionDeplacement(Pieces PieceJouable, Pieces Actuel, List<Pieces> ListePiece, Plateau plateau){
		this.PrisePiece(ListePiece, plateau, PieceJouable);
		ListePiece = this.DeplacementPiece(PieceJouable, Actuel, ListePiece);
		return ListePiece;
	}
	
	@SuppressWarnings("static-access")
	public boolean PieceEnnemie(Pieces Autre){
		System.out.println("Moi :" + this.getCouleur() + "Lui" + Autre.getCouleur() );
		if(this.getCouleur() != Autre.Couleur && Autre.getCouleur() != Couleur.Aucune){
			return true;
		}
		return false;
	}
	
	public void PrisePiece(List<Pieces> ListePiece, Plateau plateau, Pieces Autre){
		if(this.PieceEnnemie(plateau.getCase(Autre.getPositionX(), Autre.getPositionY()))){
			System.out.println(this.PieceEnnemie(plateau.getCase(Autre.getPositionX(), Autre.getPositionY())));
			ListePiece.remove(ListePiece.indexOf(plateau.getCase(Autre.getPositionX(), Autre.getPositionY())));
		}
	}
	
	public List<Pieces> DeplacementPiece(Pieces PieceJouable, Pieces Actuel, List<Pieces> ListePiece) {
		
		ListePiece.remove(ListePiece.indexOf(Actuel));
		ListePiece.add(PieceJouable);
		
		return ListePiece;
	}
	
	protected List<Pieces> PossibilitePrisePiece(List<Pieces> ListeCoup, Plateau plateau) {
		for(int i = 0; i < ListeCoup.size(); i++){
			if(plateau.getCase(ListeCoup.get(i).getPositionX(), ListeCoup.get(i).getPositionY()).getCouleur() == ListeCoup.get(i).getCouleur()){
				ListeCoup.remove(i);
				i--;
			}
		}
		return ListeCoup;
	}
	
	protected int Distance (Coup c) {
		return Math.abs(this.getPositionX()-c.getPosX() + this.getPositionY()-c.getPosY());
	}
}
