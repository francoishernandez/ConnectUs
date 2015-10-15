import java.awt.Dimension;

import javax.xml.parsers.*;

import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

import org.xml.sax.SAXException;

import java.io.File;

public class Jeu {

	private static Fenetre f;
	private static Niveau n;
	private static PanelJeu panelJeu;
	private static Partie partie;

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		// Les elements graphiques Swing doivent etre crees et utilises
	    // dans le thread de traitement des evenements de Swing. On utilise
	    // la methode invokeAndWait pour 
		
		partie = new Partie(1);
		n = partie.getNiveau();
	    try {
	      SwingUtilities.invokeAndWait(new Runnable() {
	        public void run() {

	        	try {
					f = new Fenetre();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        	
	    		PanelJeu pan = new PanelJeu(partie);
	    		/*
	    		for(int i = 0; i<=3; i++){
	    			for(int j = 0; j<=3;j++){
	    				if(Jeu.n.getPlateau()[i][j] != null){
	    					System.out.println("type "+Jeu.n.getPlateau()[i][j].getType());
	    					System.out.println("id "+Integer.toString(Jeu.n.getPlateau()[i][j].getId()));
	    					System.out.println("Position X "+Integer.toString(Jeu.n.getPlateau()[i][j].getPositionX()));
	    					System.out.println("Position Y "+Integer.toString(Jeu.n.getPlateau()[i][j].getPositionY()));
	    					System.out.println("Nord "+Integer.toString(Jeu.n.getPlateau()[i][j].getN()));
	    					
	    				}
	    			}
	    		}
	    		*/
	    		
	    		f.add(pan);
	    		f.addMouseListener(pan);
	    		f.addMouseMotionListener(pan);
	    		PanelMenu menu = new PanelMenu(partie);
	    		menu.setMenu(f);
	    		
	    		
	        }
	      });
	    } catch (Exception e) {
	      System.err.println("Erreur a la creation de l'interface Swing.");
	      System.err.println(e);
	    }


	}

}
