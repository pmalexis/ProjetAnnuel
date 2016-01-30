package moteur;

enum Type {
	Pion ('P'),
	Garde ('G'),
	Roi ('R'),
	Elephant ('E'),
	Tour ('T'),
	Bombardier ('B'),
	Cavalier ('C'),
	SansPiece('.');
	
	private final char Representation;
	
	public char getRepresentation() {
		return this.Representation;
	}

	private Type(char Representation) {
		this.Representation = Representation;
	}
}
