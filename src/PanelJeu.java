import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.*;


public class PanelJeu extends JPanel implements MouseListener, MouseMotionListener{

	private int indexX;
	private int indexY;

	private boolean blocMemoire = false;

	private BlocGraphique blocTemporaire = null;
	private int xtemp;
	private int ytemp;

	private boolean gagne;
	private boolean connexionsPlateau;

	private Partie partie;
	private int niveauCourant;

	private JButton bouton = new JButton("Niveau suivant");

	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;

	public PanelJeu(Partie p){
		this.partie = p;
		this.niveauCourant = partie.getNumeroNiveauCourant();
		this.add(bouton);
		this.bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(partie.getNumeroNiveauCourant() < partie.getNiveauMax()){
					niveauSuivant();
					bouton.setVisible(false);
				}
			}
		});
		this.bouton.setVisible(false);
	}

	public void init(){
		dim = getSize();
		offscreen = createImage(dim.width, dim.height);
		bufferGraphics = offscreen.getGraphics();
	}

	@Override
	public void update(Graphics g){
		paintComponent(g);
	}

	// DESSIN DES DIFFERENTS ELEMENTS DANS LA FENETRE

	@Override
	public void paintComponent(Graphics g){

		init();
		
		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.fillRect(0, 0, 600, 622);
		
		if((partie.getBouton() == false)&(gagne() == false)){
			bouton.setVisible(false);
		}


		// DESSIN DU TITRE

		ImageIcon titre = new ImageIcon("Images/Titre.png");
		bufferGraphics.drawImage(titre.getImage(),100,20,null);

		// DESSIN DE LA GRILLE

		bufferGraphics.setColor(Color.BLACK);

		for (int i = 100; i<=500;i+=100){
			bufferGraphics.drawLine(i,100,i,500);
		}
		for (int j = 100; j<=500; j+=100){
			bufferGraphics.drawLine(100,j,500,j);
		}

		//APPEL DES IMAGES DES BLOCS DU PLATEAU

		for(int i = 0; i<=3; i++){
			for(int j = 0; j<=3;j++){
				if(this.partie.getNiveau().getPlateau()[i][j] != null){
					this.partie.getNiveau().getPlateau()[i][j].setImageIcon(new ImageIcon("Images/"+this.partie.getNiveau().getPlateau()[i][j].getType()+".png"));
					ImageIcon cN = new ImageIcon("Images/ConnexionsNord_"+this.partie.getNiveau().getPlateau()[i][j].getN()+".png");
					bufferGraphics.drawImage(cN.getImage(), this.partie.getNiveau().getPlateau()[i][j].getPositionX(), this.partie.getNiveau().getPlateau()[i][j].getPositionY(), null);
					ImageIcon cE = new ImageIcon("Images/ConnexionsEst_"+this.partie.getNiveau().getPlateau()[i][j].getE()+".png");
					bufferGraphics.drawImage(cE.getImage(), this.partie.getNiveau().getPlateau()[i][j].getPositionX(), this.partie.getNiveau().getPlateau()[i][j].getPositionY(), null);
					ImageIcon cS = new ImageIcon("Images/ConnexionsSud_"+this.partie.getNiveau().getPlateau()[i][j].getS()+".png");
					bufferGraphics.drawImage(cS.getImage(), this.partie.getNiveau().getPlateau()[i][j].getPositionX(), this.partie.getNiveau().getPlateau()[i][j].getPositionY(), null);
					ImageIcon cO = new ImageIcon("Images/ConnexionsOuest_"+this.partie.getNiveau().getPlateau()[i][j].getW()+".png");
					bufferGraphics.drawImage(cO.getImage(), this.partie.getNiveau().getPlateau()[i][j].getPositionX(), this.partie.getNiveau().getPlateau()[i][j].getPositionY(), null);
					bufferGraphics.drawImage(this.partie.getNiveau().getPlateau()[i][j].getImage(),this.partie.getNiveau().getPlateau()[i][j].getPositionX(),this.partie.getNiveau().getPlateau()[i][j].getPositionY(),null);


				}
			}
		}

		// VICTOIRE

		if(gagne == true){
			ImageIcon victoire = new ImageIcon("Images/Victoire.png");
			bufferGraphics.drawImage(victoire.getImage(),200,500,null);

		}

		g.drawImage(offscreen,0,0,this);

		repaint();

	}

	// ROTATION AU CLIC SUR UN BLOC ET TEST CONNEXIONS (BLOC LIBRE)

	@Override
	public void mouseClicked(MouseEvent me) {

		int x = me.getX();
		int y = me.getY()-42;

		double indexi = (x/100) -1;
		double indexj = (y/100) -1;
		indexX = (int)indexi;
		indexY = (int)indexj;

		// TEST CLIC DANS LA GRILLE

		if ((0<=indexX)&(indexX<=3)&(0<=indexY)&indexY<=3){

			// CLIC SUR UNE CASE VIDE

			if (this.partie.getNiveau().getPlateau()[indexX][indexY] == null){
				//System.out.println("Bloc null");
			} else {
				if (this.partie.getNiveau().getPlateau()[indexX][indexY].getType() == "BlocLibre"){
					//System.out.println(this.partie.getNiveau().getPlateau()[indexX][indexY].getType());
					// ROTATION
					this.partie.getNiveau().getPlateau()[indexX][indexY].actionClic();
					// TEST CONNEXIONS
					//System.out.println("Connexion dernier bloc "+this.partie.getNiveau().getPlateau()[indexX][indexY].connexions());
				}

			}
		}

		this.gagne();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent me) {
	}


	// ROTATION AU CLIC SUR UN BLOC ET TEST CONNEXIONS (BLOC PIVOTE et TRANSLATE)

	@Override
	public void mouseReleased(MouseEvent me) {

		int x = me.getX();
		int y = me.getY()-42;

		// RECUPERATION DES INDICES DU BLOC

		double indexi = (x/100) -1;
		double indexj = (y/100) -1;
		indexX = (int)indexi;
		indexY = (int)indexj;

		// TEST CLIC DANS LA GRILLE

		if ((0<=indexX)&(indexX<=3)&(0<=indexY)&indexY<=3){

			// CLIC SUR UNE CASE VIDE

			if (this.partie.getNiveau().getPlateau()[indexX][indexY] == null){
				//System.out.println("Bloc null");
			} else {
				if (this.partie.getNiveau().getPlateau()[indexX][indexY].getType() != "BlocLibre"){
					//System.out.println(this.partie.getNiveau().getPlateau()[indexX][indexY].getType());
					// ROTATION
					this.partie.getNiveau().getPlateau()[indexX][indexY].actionClic();
					// CONNEXIONS
					//System.out.println("Connexion dernier bloc "+this.partie.getNiveau().getPlateau()[indexX][indexY].connexions());

				} else if (this.partie.getNiveau().getPlateau()[indexX][indexY].getType() == "BlocLibre"){
					//System.out.println("Connexion dernier bloc "+this.partie.getNiveau().getPlateau()[indexX][indexY].connexions());

				}

			}
		}

		// SET BLOCMEMOIRE A FALSE POUR AUTORISER LA SELECTION DU BLOC SUIVANT
		blocMemoire = false;

		this.gagne();
	}

	// DEPLACEMENT PAR GLISSEMENT D'UN BLOC

	@Override
	public void mouseDragged(MouseEvent me) {

		int x = me.getX();
		int y = me.getY()-42;

		// RECUPERATION DES INDICES DU BLOC

		double indexi = (x/100) -1;
		double indexj = (y/100) -1;
		indexX = (int)indexi;
		indexY = (int)indexj;
		//System.out.println("index X et Y "+indexX+", "+indexY);

		// TEST CLIC DANS LA GRILLE

		if ((0<=indexX)&(indexX<=3)&(0<=indexY)&(indexY<=3)){

			// TIMER

			// SELECTION D'UN BLOC NON VIDE

			if(this.partie.getNiveau().getPlateau()[indexX][indexY] != null){

				//System.out.println("non null "+this.partie.getNiveau().getPlateau()[indexX][indexY].getType());

				// SELECTION DU BLOC A DEPLACER

				if(this.partie.getNiveau().getPlateau()[indexX][indexY].getType() != "BlocPivote"){

					//System.out.println(blocMemoire);

					if (blocMemoire == false){
						//indexX = (int)(me.getX()/100 -1);
						//indexY = (int)(me.getY()/100 -1);
						if(this.partie.getNiveau().getPlateau()[indexX][indexY] != null){
							blocTemporaire = this.partie.getNiveau().getPlateau()[indexX][indexY];
							xtemp = indexX;
							ytemp = indexY;
							blocMemoire = true;
							//System.out.println("BlocTemporaire "+blocTemporaire.getX()+", "+blocTemporaire.getY());
							//System.out.println(blocMemoire);
						}
					}
				} 

				// ACTION POUR UN BLOC PIVOTE

				else {

				}
			} 

			// DEPLACEMENT DU BLOC SELECTIONNE SUR UNE CASE VIDE

			else if (this.partie.getNiveau().getPlateau()[indexX][indexY] == null)

				if (blocMemoire == true){
					//System.out.println("test");
					//indexX = (int)(me.getX()/100 -1);
					//indexY = (int)(me.getY()/100 -1);
					blocTemporaire.setX(indexX+1);
					blocTemporaire.setY(indexY+1);
					this.partie.getNiveau().getPlateau()[indexX][indexY] = blocTemporaire;
					if (!((indexX == xtemp)&(indexY == ytemp))){
						this.partie.getNiveau().getPlateau()[xtemp][ytemp] = null;
						blocTemporaire = null;
						//System.out.println("x et y temp"+xtemp+", "+ytemp);
						//System.out.println("ok");
					}

					blocMemoire = false;
				}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {

	}

	// TEST DE VICTOIRE
	
	public boolean gagne(){

		// TEST CONNEXIONS BLOCS NON NULL
		connexionsPlateau = true;
		for(int i = 0; i<=3; i++){
			for(int j = 0; j<=3;j++){
				if(this.partie.getNiveau().getPlateau()[i][j] != null){
					connexionsPlateau = connexionsPlateau & this.partie.getNiveau().getPlateau()[i][j].connexions();
					//System.out.println("Bloc "+i+", "+j+" : "+this.partie.getNiveau().getPlateau()[i][j].connexions());
				}
			}
		}

		if (connexionsPlateau == true){
			gagne = true;
			this.bouton.setVisible(true);
		} else {
			gagne = false;
			this.bouton.setVisible(false);
		}

		return gagne;
	}

	// APPEL DU NIVEAU SUIVANT
	
	public void niveauSuivant(){
		if(gagne == true){
			this.partie.goToNextLevel();
			//System.out.println("Aller au niveau suivant");
			gagne = false;
			connexionsPlateau = false;

		}
	}


}
