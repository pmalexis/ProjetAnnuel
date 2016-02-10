package ihm;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Controleur;

@SuppressWarnings("serial")
public class IhmXiangqi extends JFrame {

	VisuXiangqi  visuChess;
	Controleur   controleur;
	
	JLabel label;
	
	/*
	 * Controleur
	 */
	public IhmXiangqi() {
		
		this.setTitle("Jeu du Xiangqi");
		this.setLocation(100, 100);
		this.setSize(600, 640);
		
		this.controleur = new Controleur();
		this.visuChess  = new VisuXiangqi(this.controleur, this);
		this.add(visuChess);
		
		this.label = new JLabel("C'est au joueur 'rouge' de commencer", SwingConstants.CENTER);
		this.add(this.label, BorderLayout.SOUTH);
		
		//gerer la fermeture fenetre
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		} );

		this.setVisible(true); 
	}
	
	public void changerLabel(String str) {
		this.label.setText(str);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		IhmXiangqi ihmXiangqi = new IhmXiangqi();
	}
}
