package ihm;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import java.util.List;

import controleur.Controleur;
import moteur.Type;
import moteur.Coup;

/*
 * Penser à mettre les images en fond transparent !!!
 */
@SuppressWarnings("serial")
public class VisuChess extends JPanel implements MouseListener, MouseMotionListener {

	Controleur controleur;
	
	int tailleX;
	int tailleY;

	private int[] tabCoord; //coordonnée x,y de l'endroit cliqué par le joueur

	private int[] tabMouse;

	private boolean firstClick = true;

	private List<Coup> listCoupPossible = null;
	
	/*
	 * Controleur
	 */
	public VisuChess(Controleur controleur) {
		this.setBackground(Color.WHITE);
		
		this.controleur = controleur;
	
		String[][] tabChess = this.controleur.getTabChess();
		this.tailleX = tabChess.length;
		this.tailleY = tabChess[0].length;
		
		this.tabCoord = new int[2];
		for(int i=0;i<this.tabCoord.length;i++)
			this.tabCoord[i] = -1;
		
		this.tabMouse = new int[2];
		for(int i=0;i<this.tabMouse.length;i++)
			this.tabMouse[i] = -1;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	/*
	 * Methode qui s'appelle pour dessiner
	 */
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		
		Image image = null;
		String[][] tabChess = this.controleur.getTabChess();
		
		Image imageTempo = null;
		int[] tabCoordImage = new int[2];
		for(int i=0;i<tabCoordImage.length;i++)
			tabCoordImage[i] = -1;
		
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
						if(j==0) g.drawRect(posX, posY, posX*(tailleX-2), getHeight()/(tailleY+2));
					}
					else g.drawRect(posX, posY, getWidth()/(tailleX), getHeight()/(tailleY+2));
				}
				
				//Dessine les images
				if(tabChess[i][j].length() > 0) image = getToolkit().getImage("res/img/"+tabChess[i][j]+".png");
				else image = null;
				
				if(!(this.tabCoord[0] == i && this.tabCoord[1] == j))
					g.drawImage(image, posX-((getWidth()/(tailleX))/2), posY-((getHeight()/(tailleY+2))/2), getWidth()/(tailleX), getHeight()/(tailleY+2), null);
				else {
					tabCoordImage[0] = tailleX;
					tabCoordImage[1] = tailleY;
					imageTempo = image;
				}
				
				g.setColor(Color.GREEN);
				Graphics2D g2d = (Graphics2D)g;
				Composite original = g2d.getComposite();
				Composite translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
				g2d.setComposite(translucent);
				if(this.listCoupPossible != null)
					for(int l=0;l<this.listCoupPossible.size();l++)
						if(this.listCoupPossible.get(l).getPosX() == i && this.listCoupPossible.get(l).getPosY() == j)
							g.fillOval(posX-((getWidth()/(tailleX))/2), posY-((getHeight()/(tailleY+2))/2), getWidth()/(tailleX), getHeight()/(tailleY+2));
				g.setColor(Color.BLACK);
				g2d.setComposite(original);
				
				posX += this.getWidth()/(tailleX);
			}
			posY += this.getHeight()/(tailleY+2);
		}
		
		if(tabCoordImage[0] > -1)
			g.drawImage(imageTempo, this.tabMouse[0]-((getWidth()/(tabCoordImage[0]))/2), this.tabMouse[1]-((getHeight()/(tabCoordImage[1]+2))/2), getWidth()/(tabCoordImage[0]), getHeight()/(tabCoordImage[1]+2), null);
	}

	
	/*
	 * Permet de choisir une case du tableau celon l'endroit ou le clique de la souris a ete effectue
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
	
	
	/****************************************************************
	 * Methodes qui permettent de detecter les cliques de la souris *
	 ****************************************************************/
	
	/*
	 * Methode qui s'appelle que quand il y a un clique de la souris
	 */
	public void mouseClicked(MouseEvent me) {
		if(this.firstClick) {
			this.tabCoord = this.caseChoisi(me.getX(), me.getY());
			if(this.tabCoord[0] < 0) this.firstClick = true;
			else this.firstClick = false;
			
			if(this.controleur.getType(this.tabCoord) == Type.SansPiece) {
				this.firstClick = true;
				//this.tabCoord[0] = -1;
			}
			
			if(!this.firstClick) {
				this.listCoupPossible  = this.controleur.getListCoupPossible(this.tabCoord[0], this.tabCoord[1]);
				
				for(int i=0;i<this.listCoupPossible.size();i++)
					System.out.println("[" + this.listCoupPossible.get(i).getPosX() + "," + this.listCoupPossible.get(i).getPosY() + "]");
				System.out.println("--------");
			}
		}	
		else {
			int[] tabCoordFinal = this.caseChoisi(me.getX(), me.getY());
			//function controleur boolean bool = jouer(this.tabCoord, tabCoordFinal);
			//selon bool tab à -1
			this.tabCoord[0] = -1;
			this.firstClick = true;
			this.listCoupPossible = null;
		}
		
		if(this.tabCoord[0] > -1)
			System.out.println("Vous avez clique sur : " + this.controleur.getTabChess()[this.tabCoord[0]][this.tabCoord[1]]);
		
		repaint();
	}
	
	/*
	 * Methode qui s'appelle à chaque mouvement de la souris
	 */
	public void mouseMoved(MouseEvent e) {
		if(!(this.tabCoord[0] == -1 && this.tabCoord[1] == -1)) {
			this.tabMouse[0] = e.getX();
			this.tabMouse[1] = e.getY();
			repaint();
		}
		else {
			this.tabMouse[0] = -1;
			this.tabMouse[1] = -1;
		}
	 }
	
	public void mouseEntered (MouseEvent me) {repaint();}
	public void mouseExited  (MouseEvent me) {}
	public void mousePressed (MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseDragged (MouseEvent me) {}
}
