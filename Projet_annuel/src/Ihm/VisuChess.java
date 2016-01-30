package Ihm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Controleur.Controleur;

/*
 * Penser à mettre les images en fond transparent
 */
@SuppressWarnings("serial")
public class VisuChess extends JPanel implements MouseListener {

	Controleur controleur;
	
	int tailleX;
	int tailleY;
	
	public VisuChess(Controleur controleur) {
		this.controleur = controleur;
		
		this.addMouseListener(this);
	
		String[][] tabChess = this.controleur.getTabChess();
		this.tailleX = tabChess.length;
		this.tailleY = tabChess[0].length;
	}
	
	public void paint(Graphics g) {
		
		Image image = null;
		String[][] tabChess = this.controleur.getTabChess();
		
		int posX = 0;
		int posY = this.getHeight()/(tailleY+2);
		
		//Les quatres lignes en diagonale du plateau
		g.drawLine(this.getWidth()/(tailleX)*4, this.getHeight()/(tailleY+2), this.getWidth()/(tailleX)*6, this.getHeight()/(tailleY+2)*3);
		g.drawLine(this.getWidth()/(tailleX)*6, this.getHeight()/(tailleY+2), this.getWidth()/(tailleX)*4, this.getHeight()/(tailleY+2)*3);
		g.drawLine(this.getWidth()/(tailleX)*4, this.getHeight()/(tailleY+2)*8, this.getWidth()/(tailleX)*6, this.getHeight()/(tailleY+2)*10);
		g.drawLine(this.getWidth()/(tailleX)*6, this.getHeight()/(tailleY+2)*8, this.getWidth()/(tailleX)*4, this.getHeight()/(tailleY+2)*10);

		for(int i=0;i<tailleX;i++) {
			
			posX = this.getWidth()/(tailleX);
			
			for(int j=0;j<tailleY;j++) {
				
				//Dessine le plateau
				if(i != tabChess.length-1 &&  j != tabChess[0].length-1) {
					if(i==4) {
						g.drawRect(posX, posY, posX*(tailleX-2), getHeight()/(tailleY+2));
						break;
					}
					else g.drawRect(posX, posY, getWidth()/(tailleX), getHeight()/(tailleY+2));
				}
				
				//Dessine les images
				if(tabChess[i][j].length() > 0) image = getToolkit().getImage("res/img/"+tabChess[i][j]+".png");
				else image = null;
				
				g.drawImage(image, posX-((getWidth()/(tailleX))/2), posY-((getHeight()/(tailleY+2))/2), getWidth()/(tailleX), getHeight()/(tailleY+2), null);
				
				
				posX += this.getWidth()/(tailleX);
			}
			posY += this.getHeight()/(tailleY+2);
			
		}
	}

	
	/*
	 * Permet de choisir une case
	 */
	public int[] caseChoisi(int x, int y) 
	{	
		int posX = 0;
 		int posY = this.getHeight()/(tailleY+2)-((getHeight()/(tailleY+2))/2);
		
		int[] tabCord = new int[2];
		
		for(int i=0;i<tailleX;i++)
		{
			posX = this.getWidth()/(tailleX) - ((getWidth()/(tailleX))/2);
		
			for(int j=0;j<tailleY;j++)
			{
				if(posX<=x && posY<=y && (posX + this.getWidth()/(tailleX))>=x && (posY + this.getHeight()/(tailleY+2))>=y)
				{
					tabCord[0] = i;
					tabCord[1] = j;
					
					return tabCord;
				}		
				posX += this.getWidth()/(tailleX);
			}
			posY += this.getHeight()/(tailleY+2);
		}
		
		tabCord[0] = -1;
		tabCord[1] = -1;
		
		return tabCord;
	}
	
	/*
	 * Methodes qui permettent de detecter les cliques de la souris
	 */
	
	public void mouseClicked(MouseEvent me) {
		int[] tabCoord = this.caseChoisi(me.getX(), me.getY());
		if(tabCoord[0] > -1)
			System.out.println("Vous avez clique sur : " + this.controleur.getTabChess()[tabCoord[0]][tabCoord[1]]);
	}
	
	public void mouseEntered(MouseEvent me) {repaint();}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	
	
}
