package moteur;

import java.util.List;

public class Pion extends Pieces {

	public Pion(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "P");
	}

	@Override
	public List<Coup> ListeCoupPossible(List<Coup> ListeCoup, Plateau plateau) {
		return null;
	}

	@Override
	public List<Coup> ListeCoup() {
		return null;
	}

}
