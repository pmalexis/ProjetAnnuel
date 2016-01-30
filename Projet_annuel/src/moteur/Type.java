package moteur;

enum Type {
	
	Pion ('P'),
	Garde ('G'),
	Roi ('R'),
	Elephant ('E'),
	Bombardier ('B'),
	Cavalier ('C'),
	Tour ('T'),
	SansPiece ('.');
	
	private final char Representation;
	
	private Type (char Representation) {
		this.Representation = Representation;
	}
	
	public char getRepresentation() {
		return this.Representation;
	}
}