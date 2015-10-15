import java.awt.Color;
import java.awt.event.MouseEvent;


public class BlocTranslate extends BlocGraphique {
	
	private String type = "BlocTranslate";
	
	boolean i = true;
	
	int followX;
	int followY;
	
	public BlocTranslate(int i, Niveau niv){
		super(i,niv);
	}
	
	public String getType(){
		return this.type;
	}

	public void actionClic() {
		
	}

}
