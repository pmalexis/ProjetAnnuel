package moteur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Evaluateur {
	
	private Plateau plat;
	static HashMap<Type,List<Integer>> valeurPieces = new HashMap<Type,List<Integer>>();
	
	static int valeurPiecesLoc[][][] = 
		{
			{
				{
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0
				},
				{
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0,
			        0, 0, 0, 0, 0, 0, 0, 0, 0
				}
			},
			{
				{ // Blue pawns...
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					10,  0, 12,  0, 15,  0, 12,  0, 10,
					10,  0, 13,  0, 10,  0, 13,  0, 10,
					20, 20, 20, 20, 20, 20, 20, 20, 20,
					20, 21, 21, 22, 22, 22, 21, 21, 20,
					20, 21, 21, 23, 23, 23, 21, 21, 20,
					20, 21, 21, 23, 22, 23, 21, 21, 20,
					11, 12, 13, 14, 14, 14, 13, 12, 11
			},
				{ // Red pawns...
					11, 12, 13, 14, 14, 14, 13, 12, 11,
					20, 21, 21, 23, 22, 23, 21, 21, 20,
					20, 21, 21, 23, 23, 23, 21, 21, 20,
					20, 21, 21, 22, 22, 22, 21, 21, 20,
					20, 20, 20, 20, 20, 20, 20, 20, 20,
					10,  0, 13,  0, 10,  0, 13,  0, 10,
					10,  0, 12,  0, 15,  0, 12,  0, 10,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
				}
			},
			{
				{ // Blue canon...
					50, 50, 50, 50, 50, 50, 50, 50, 50, 
					50, 50, 50, 50, 50, 50, 50, 50, 50,
					50, 51, 53, 53, 55, 53, 53, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 51, 51, 51, 51, 51, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 50, 50, 50, 50, 50, 50, 50, 50
				},
				{ // Red canon...
					50, 50, 50, 50, 50, 50, 50, 50, 50,                 
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 51, 51, 51, 51, 51, 51, 50,
					50, 51, 50, 50, 50, 50, 50, 51, 50,
					50, 51, 53, 53, 55, 53, 53, 51, 50,
					50, 50, 50, 50, 50, 50, 50, 50, 50,
					50, 50, 50, 50, 50, 50, 50, 50, 50
				}
			},
			{
				{ // Blue cart...
					89, 92, 90, 90, 90, 90, 90, 92, 89,
					91, 92, 90, 93, 90, 93, 90, 92, 91,
					90, 92, 90, 91, 90, 91, 90, 92, 90,
					90, 91, 90, 91, 90, 91, 90, 91, 90,
					90, 94, 90, 94, 90, 94, 90, 94, 90,
					90, 93, 90, 91, 90, 91, 90, 93, 90,
					90, 91, 90, 91, 90, 91, 90, 91, 90,
					90, 91, 90, 90, 90, 90, 90, 91, 90,
					90, 92, 91, 91, 90, 91, 91, 92, 90,
					90, 90, 90, 90, 90, 90, 90, 90, 90
				},
				{ // Red cart...
					90, 90, 90, 90, 90, 90, 90, 90, 90,
					90, 92, 91, 91, 90, 91, 91, 92, 90,
					90, 91, 90, 90, 90, 90, 90, 91, 90,
					90, 91, 90, 91, 90, 91, 90, 91, 90,
					90, 93, 90, 91, 90, 91, 90, 93, 90,
					90, 94, 90, 94, 90, 94, 90, 94, 90,
					90, 91, 90, 91, 90, 91, 90, 91, 90,
					90, 92, 90, 91, 90, 91, 90, 92, 90,
					91, 92, 90, 93, 90, 93, 90, 92, 91,
					89, 92, 90, 90, 90, 90, 90, 92, 89
				}
			},
			{
				{ // Blue Knight...
					40, 35, 40, 40, 40, 40, 40, 35, 40,
					40, 41, 42, 40, 20, 40, 42, 41, 40,
					40, 42, 43, 40, 40, 40, 43, 42, 40,
					40, 42, 43, 43, 43, 43, 43, 42, 40,
					40, 43, 44, 44, 44, 44, 44, 43, 40,
					40, 43, 44, 44, 44, 44, 44, 43, 40,
					40, 43, 44, 44, 44, 44, 44, 43, 40,
					40, 43, 44, 44, 44, 44, 44, 43, 40,
					40, 41, 42, 42, 42, 42, 42, 41, 40,
					40, 40, 40, 40, 40, 40, 40, 40, 40
				},
				{ // Red Knight
					40, 40, 40, 40, 40, 40, 40, 40, 40,
					40, 41, 42, 42, 42, 42, 42, 41, 40,
					40, 43, 44, 44, 44, 44, 44, 43, 40,
					40, 43, 44, 44, 44, 44, 44, 43, 40,
					40, 43, 44, 44, 44, 44, 44, 43, 40,
					40, 43, 44, 44, 44, 44, 44, 43, 40,
					40, 42, 43, 43, 43, 43, 43, 42, 40,
					40, 42, 43, 40, 40, 40, 43, 42, 40,
					40, 41, 42, 40, 20, 40, 42, 41, 40,
					40, 35, 40, 40, 40, 40, 40, 35, 40
				}
			},
			{
				{ // Blue elephant...
					 0,  0, 25,  0,  0,  0, 25,  0,  0,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
					23,  0,  0,  0, 28,  0,  0,  0, 23,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
					 0,  0, 22,  0,  0,  0, 22,  0,  0,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
					 0,  0,  0,  0,  0,  0,  0,  0,  0,
					 0,  0,  0,  0,  0,  0,  0,  0,  0
				},
				{ // Red elephant...
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0, 22,  0,  0,  0, 22,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					23,  0,  0,  0, 28,  0,  0,  0, 23,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0, 25,  0,  0,  0, 25,  0,  0
				}
			},
			{
				{ // Blue guard...
					0,  0,  0, 20,  0, 20,  0,  0,  0,
					0,  0,  0,  0, 22,  0,  0,  0,  0,
					0,  0,  0, 19,  0, 19,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0
				},
				{ // Red guard...
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0, 19,  0, 19,  0,  0,  0,
					0,  0,  0,  0, 22,  0,  0,  0,  0,
					0,  0,  0, 20,  0, 20,  0,  0,  0
				}
			},
			{
				{ // blue king...
					0,  0,  0, 30, 35, 30,  0,  0,  0,
					0,  0,  0, 15, 15, 15,  0,  0,  0,
					0,  0,  0,  1,  1,  1,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0
				},
				{ // red king...
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  0,  0,  0,  0,  0,  0,
					0,  0,  0,  1,  1,  1,  0,  0,  0,
					0,  0,  0, 15, 15, 15,  0,  0,  0,
					0,  0,  0, 30, 35, 30,  0,  0,  0
				}
			}
		};

	
	public Evaluateur (Plateau plat) {
		this.plat = plat;
		initEvaluateur();
	}
	
	private void initEvaluateur() {
		List<Integer> val = new ArrayList<Integer>();
		val.add(0); val.add(0);
		Evaluateur.valeurPieces.put(Type.SansPiece, val);
		val = new ArrayList<Integer>();
		val.add(1); val.add(10);
		Evaluateur.valeurPieces.put(Type.Pion, val);
		val = new ArrayList<Integer>();
		val.add(2); val.add(45);
		Evaluateur.valeurPieces.put(Type.Bombardier, val);
		val = new ArrayList<Integer>();
		val.add(3); val.add(90);
		Evaluateur.valeurPieces.put(Type.Tour, val);
		val = new ArrayList<Integer>();
		val.add(4); val.add(40);
		Evaluateur.valeurPieces.put(Type.Cavalier, val);
		val = new ArrayList<Integer>();
		val.add(5); val.add(20);
		Evaluateur.valeurPieces.put(Type.Elephant, val);
		val = new ArrayList<Integer>();
		val.add(6); val.add(20);
		Evaluateur.valeurPieces.put(Type.Garde, val);
		val = new ArrayList<Integer>();
		val.add(7); val.add(1500);
		Evaluateur.valeurPieces.put(Type.Roi, val);
	}
	
	private long valeurPosition() {
		Couleur amie = plat.getJoueurAct();
		Couleur ennemie = amie == Couleur.Noir ? Couleur.Rouge : Couleur.Noir;
		long tot = 0;
		for(Entry<Type, List<Integer>> entry : valeurPieces.entrySet()) {
		    Type type = entry.getKey();
		    List<Integer> valeur = entry.getValue();
		    List<Integer> indexAmie = plat.indexUnitees(type, amie);
		    List<Integer> indexEnnemie = plat.indexUnitees(type, ennemie);
		    
		    for (int j = 0; j < indexAmie.size(); j++)
		    	tot += valeurPiecesLoc[valeur.get(0)][0][indexAmie.get(j)];
		    
		    for (int j = 0; j < indexEnnemie.size(); j++)
		    	tot -= valeurPiecesLoc[valeur.get(0)][1][indexEnnemie.get(j)];
		}
		if (plat.estEchec(ennemie)) {
			tot += 100;
		} else if (plat.estEchec(amie)) {
			tot -= 100;
		}
		return tot;
	}
	
	/*private long valeurPlateau() {
		Couleur amie = plat.getJoueurAct();
		Couleur ennemie = amie == Couleur.Noir ? Couleur.Rouge : Couleur.Noir;
		long tot = 0;
		for(Entry<Type, List<Integer>> entry : valeurPieces.entrySet()) {
		    Type type = entry.getKey();
		    List<Integer> valeur = entry.getValue();
		    tot += plat.indexUnitees(type, amie).size() * valeur.get(1);
			tot -= plat.indexUnitees(type, ennemie).size() * valeur.get(1);
		}
		return tot;
	}*/
	
	public long evaluation() {
		return valeurPosition();
	}
	
	/*public int valeurPiece (Pieces p) {
		List<Integer> key = valeurPieces.get(p.getType());
		return key.get(1);
	}*/
}
