import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public abstract class BlocGraphique {

	private int idBloc;
	private String type;

	// CoordonnŽes Tableau
	int x;
	int y;

	// Position absolue dans la fentre
	int positionX;
	int positionY;

	int[] connexionsFixes ;
	int[] connexionsEffectives ;

	private boolean connexionsVoisins;
	Niveau niveau;

	// Image

	private ImageIcon icn;
	private Image img;

	// CONSTRUCTEUR
	public BlocGraphique(int i, Niveau niv){
		this.idBloc = i;
		this.connexionsFixes = new int[4];
		this.connexionsEffectives = new int[4];
		this.connexionsVoisins = false;
		this.niveau = niv;
	}

	public int getId(){
		return this.idBloc;
	}

	public abstract String getType();

	public abstract void actionClic();


	public boolean connexions(){

		boolean nord = false, sud = false, est = false, ouest = false;

		int xTab = x-1;
		int yTab = y-1;

		//System.out.println(xTab+", "+yTab);

		// GESTION CONNEXION NORD

		// BLOC NON DANS LA PREMIERE LIGNE

		if (yTab > 0){
			//System.out.println(this.getN()+", "+this.getE()+", "+this.getS()+", "+this.getW());

			// VOISIN NORD NULL

			if(niveau.getPlateau()[xTab][yTab-1] == null){

				nord = (this.getN() == 0);

			} 

			// VOISIN NORD NON NULL

			else {

				if(this.getN() != 0) {
					nord = ((this.getN()-niveau.getPlateau()[xTab][yTab-1].getS()) == 0);
				} else {
					nord = (niveau.getPlateau()[xTab][yTab-1].getS() == 0);
				}
			}
		} 

		// BLOC DANS LA PREMIERE LIGNE

		else {
			nord = (this.getN() == 0);
		}


		// GESTION CONNEXION EST

		// BLOC NON DANS LA DERNIERE COLONNE

		if (xTab < 3){
			//System.out.println(this.getN()+", "+this.getE()+", "+this.getS()+", "+this.getW());

			// VOISIN EST NULL

			if(niveau.getPlateau()[xTab+1][yTab] == null){

				est = (this.getE() == 0);

				//System.out.println("Voisin EST null");

			} 

			// VOISIN EST NON NULL

			else {

				if(this.getE() != 0) {
					est = ((this.getE() - niveau.getPlateau()[xTab+1][yTab].getW()) == 0);

				} else {
					est = (niveau.getPlateau()[xTab+1][yTab].getW() == 0);
				}
			}
		} 

		// BLOC DANS LA DERNIERE COLONNE

		else {
			est = (this.getE() == 0);
		}


		// GESTION CONNEXION SUD
		
		// BLOC NON DANS LA DERNIERE LIGNE

		if (yTab < 3){
			//System.out.println(this.getN()+", "+this.getE()+", "+this.getS()+", "+this.getW());
			
			// VOISIN SUD NULL

			if(niveau.getPlateau()[xTab][yTab+1] == null){

				sud = (this.getS() == 0);

			} 
			
			// VOISIN SUD NON NULL
			
			else {

				if(this.getS() != 0) {
					sud = ((this.getS() - niveau.getPlateau()[xTab][yTab+1].getN()) == 0);

				} else {
					sud = (niveau.getPlateau()[xTab][yTab+1].getN() == 0);
				}
			}
		} 

		// BLOC DANS LA DERNIERE LIGNE

		else {
			sud = (this.getS() == 0);
		}

		// GESTION CONNEXION OUEST
		
		// BLOC NON DANS LA PREMIERE COLONNE

		if (xTab > 0){
			//System.out.println(this.getN()+", "+this.getE()+", "+this.getS()+", "+this.getW());
			
			// VOISIN SUD NULL

			if(niveau.getPlateau()[xTab-1][yTab] == null){

				ouest = (this.getW() == 0);
				

			} 
			
			// VOISIN SUD NON NULL
			
			else {

				if(this.getW() != 0) {
					ouest = ((this.getW() - niveau.getPlateau()[xTab-1][yTab].getE()) == 0);

				} else {
					ouest = (niveau.getPlateau()[xTab-1][yTab].getE() == 0);
				}
			}
		} 

		// BLOC DANS LA DERNIERE LIGNE

		else {
			ouest = (this.getW() == 0);
		}

		return nord & est & sud & ouest;		
	}


	// Gestion de l'image

	public void setImageIcon(ImageIcon icon){
		this.icn = icon;
		this.img = icn.getImage();
	}

	public Image getImage(){
		return this.img;
	}

	//Gestion de la position

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void setX(int i){
		this.x = i;
		this.positionX = 100*this.x;
	}

	public void setY(int i){
		this.y = i;
		this.positionY = 100*this.y;
	}

	public void setXY(int i, int j){
		this.x = i;
		this.y = j;
		this.positionX = 100*this.x;
		this.positionY = 100*this.y;
	}

	public void setPositionX(int x){
		this.positionX = x;
	}

	public void setPositionY(int y){
		this.positionY = y;
	}

	public int getPositionX(){
		return this.positionX;
	}

	public int getPositionY(){
		return this.positionY;
	}


	// GETTERs et SETTERs de connexions

	public void setConnexionsFixes(int haut, int droite, int bas, int gauche){
		this.connexionsFixes[0] = haut;
		this.connexionsFixes[1] = droite;
		this.connexionsFixes[2] = bas;
		this.connexionsFixes[3] = gauche;
		for (int i=0;i<=3;i++){
			this.connexionsEffectives[i] = this.connexionsFixes[i];
		}
	}

	public int[] getConnexionsFixes(){
		return this.connexionsFixes;
	}

	public int[] getConnexionsEffectives(){
		return this.connexionsEffectives;
	}

	public int getN(){
		return this.connexionsEffectives[0];
	}

	public int getE(){
		return this.connexionsEffectives[1];
	}

	public int getS(){
		return this.connexionsEffectives[2];
	}

	public int getW(){
		return this.connexionsEffectives[3];
	}


}
