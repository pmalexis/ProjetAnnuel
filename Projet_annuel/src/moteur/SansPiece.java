package moteur;

import java.util.List;

public class SansPiece extends Pieces {

	public SansPiece(int PositionX, int PositionY, Type Type, Couleur Couleur) {
		super(PositionX, PositionY, Type, Couleur, "V");
	}

	@Override
	public List<Coup> ListeCoupPossible(List<Coup> ListCoup, Plateau plateau) {
		return null;
	}
}
