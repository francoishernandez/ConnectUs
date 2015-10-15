
public class Partie {

	private int numeroNiveauCourant;
	private Niveau niveauCourant = new Niveau();
	private int niveauMax = 8;
	private boolean etatBouton;

	public Partie(int i){
		numeroNiveauCourant = i;
		this.niveauCourant.setPlateau(i);
		this.niveauCourant.setPartie(this);
	}
	
	public void setBouton(boolean b){
		this.etatBouton = b;
	}
	
	public boolean getBouton(){
		return this.etatBouton;
	}

	public int getNumeroNiveauCourant(){
		return this.numeroNiveauCourant;
	}
	
	public int getNiveauMax(){
		return this.niveauMax;
	}

	public void setNumeroNiveauCourant(int i){
		this.numeroNiveauCourant = i;
		this.setNiveau();
	}

	public void goToNextLevel(){
		if(numeroNiveauCourant<niveauMax){
			this.setNumeroNiveauCourant(numeroNiveauCourant+1);
		}
	}

	public void setNiveau(){
		this.niveauCourant = new Niveau(numeroNiveauCourant, this);
	}

	public Niveau getNiveau(){
		return this.niveauCourant;
	}

}
