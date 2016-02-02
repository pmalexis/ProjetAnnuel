package ihm;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controleur.Controleur;

@SuppressWarnings("serial")
public class IhmChess extends JFrame {

	VisuChess  visuChess;
	Controleur controleur;
	
	/*
	 * Controleur
	 */
	public IhmChess() {
		
		this.setTitle("Jeu du Xiangqi");
		this.setLocation(100, 100);
		this.setSize(600, 640);
	
		this.controleur = new Controleur();
		this.visuChess  = new VisuChess(this.controleur);
		this.add(visuChess);
		
		//gerer la fermeture fenetre
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		} );

		this.setVisible(true); 
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		IhmChess ihmChess = new IhmChess();
	}
}
