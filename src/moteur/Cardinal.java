package moteur;

enum Cardinal {

    Nord(-1, 0),       /** 0) Direction Nord.       */
    NordEst(-1, +1),   /** 1) Direction Nord-Est.   */
    Est(0, +1),        /** 2) Direction Est.        */  
    SudEst(+1, +1),    /** 3) Direction Sud-Est.    */
    Sud(+1, 0),        /** 4) Direction Sud.        */
    SudOuest(+1, -1),  /** 5) Direction Sud-Ouest.  */
    Ouest(0, -1),      /** 6) Direction Ouest.      */  
    NordOuest(-1, -1); /** 7) Direction Nord-Ouest. */
	
	private int PosX;
	private int PosY;
	
	private Cardinal(int PosX, int PosY){
		this.PosX = PosX;
		this.PosY = PosY;
	}

	public int getPosY() {
		return PosY;
	}

	public int getPosX() {
		return PosX;
	}
	
	public Cardinal Opposee() {
		return values()[(ordinal() + 4) % values().length];
	}
}
