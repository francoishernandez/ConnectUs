import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Niveau {

	private Partie partie;

	private BlocGraphique[][] plateau = new BlocGraphique[4][4];

	public Niveau(int i, Partie p){
		this.setPlateau(i);
		this.partie = p;
	}

	public Niveau(Partie p){
		this.partie = p;
	}
	public Niveau(){
		
	}
	
	public void setPartie(Partie p){
		this.partie = p;
	}


	public BlocGraphique[][] getPlateau(){
		return this.plateau;
	}

	public void setPlateau(int numero){

		// RECUPERATION D'UNE INSTANCE DE LA CLASSE DOCUMENTBUILDERFACTORY

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			// CREATION D'UN PARSEUR

			final DocumentBuilder builder = factory.newDocumentBuilder();

			// CREATION D'UN DOCUMENT

			final Document document= builder.parse(new File("Niveaux/Niveau_"+numero+".xml"));

			// AFFICHAGE DU PROLOGUE

			/*
			System.out.println("*************PROLOGUE************");
			System.out.println("version : " + document.getXmlVersion());
			System.out.println("encodage : " + document.getXmlEncoding());		
			System.out.println("standalone : " + document.getXmlStandalone());
			*/


			// RECUPERATION DE L'ELEMENT RACINE (ici les blocs)

			final Element racine = document.getDocumentElement();

			// AFFICHAGE DE L'ELEMENT RACINE DANS LA CONSOLE
			
			/*
			System.out.println("\n*************RACINE************");
			System.out.println(racine.getNodeName()+" "+numero);
			*/

			// RECUPERATION DES BLOCS

			final NodeList racineNoeuds = racine.getChildNodes();
			final int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i<nbRacineNoeuds; i++) {
				if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element bloc = (Element) racineNoeuds.item(i);

					// RECUPERATION DES ATTRIBUTS DES BLOCS

					final Element type = (Element) bloc.getElementsByTagName("type").item(0);
					final Element X = (Element) bloc.getElementsByTagName("X").item(0);
					final Element Y = (Element) bloc.getElementsByTagName("Y").item(0);
					final Element nord = (Element) bloc.getElementsByTagName("nord").item(0);
					final Element est = (Element) bloc.getElementsByTagName("est").item(0);
					final Element sud = (Element) bloc.getElementsByTagName("sud").item(0);
					final Element ouest = (Element) bloc.getElementsByTagName("ouest").item(0);

					if(type.getTextContent().equals("BlocTranslate")){
						BlocTranslate blocT = new BlocTranslate(i,this);
						blocT.setConnexionsFixes(Integer.parseInt(nord.getTextContent()), Integer.parseInt(est.getTextContent()), Integer.parseInt(sud.getTextContent()), Integer.parseInt(ouest.getTextContent()));
						blocT.setXY(Integer.parseInt(X.getTextContent())+1,Integer.parseInt(Y.getTextContent())+1);
						this.plateau[Integer.parseInt(X.getTextContent())][Integer.parseInt(Y.getTextContent())] = blocT;
					}
					if(type.getTextContent().equals("BlocPivote")){
						BlocPivote blocT = new BlocPivote(i,this);
						blocT.setConnexionsFixes(Integer.parseInt(nord.getTextContent()), Integer.parseInt(est.getTextContent()), Integer.parseInt(sud.getTextContent()), Integer.parseInt(ouest.getTextContent()));
						blocT.setXY(Integer.parseInt(X.getTextContent())+1,Integer.parseInt(Y.getTextContent())+1);
						this.plateau[Integer.parseInt(X.getTextContent())][Integer.parseInt(Y.getTextContent())] = blocT;
					}
					if(type.getTextContent().equals("BlocLibre")){
						BlocLibre blocT = new BlocLibre(i, this);
						blocT.setConnexionsFixes(Integer.parseInt(nord.getTextContent()), Integer.parseInt(est.getTextContent()), Integer.parseInt(sud.getTextContent()), Integer.parseInt(ouest.getTextContent()));
						blocT.setXY(Integer.parseInt(X.getTextContent())+1,Integer.parseInt(Y.getTextContent())+1);
						this.plateau[Integer.parseInt(X.getTextContent())][Integer.parseInt(Y.getTextContent())] = blocT;
					}

					// AFFICHAGE DES ATTRIBUTS DES BLOCS

					/*
					System.out.println("type : " + type.getTextContent());
					System.out.println("X : " + X.getTextContent());
					System.out.println("Y : " + Y.getTextContent());
					System.out.println("nord : " + nord.getTextContent());
					System.out.println("est : " + est.getTextContent());
					System.out.println("sud : " + sud.getTextContent());
					System.out.println("ouest : " + ouest.getTextContent());
					System.out.println(this.plateau[Integer.parseInt(X.getTextContent())][Integer.parseInt(Y.getTextContent())].getX());
					System.out.println(this.plateau[Integer.parseInt(X.getTextContent())][Integer.parseInt(Y.getTextContent())].getY());
					 */
				}		
			}			
		}
		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (final SAXException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}		


	}
}
