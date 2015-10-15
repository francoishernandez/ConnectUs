import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class BlocPivote extends BlocGraphique {
	
	String type = "BlocPivote";
	
	public BlocPivote(int i, Niveau niv){
		super(i,niv);
		//this.partie = p;
	}
	
	public String getType(){
		return this.type;
	}
	
	// ROTATION AU CLIC
	
	public void actionClic(){
		
		int[] temp = new int[4];
		for (int i = 0; i<=3;i++){
			temp[i]=this.connexionsEffectives[i];
		}
		for (int i=0;i<=3;i++){
			this.connexionsEffectives[(i+1)%4] = temp[i];
		}
		
		//ImageIcon icn = new ImageIcon("Images/BlocLibre_"+this.getN()+"_"+this.getE()+"_"+this.getS()+"_"+this.getW()+".png");
		//img = icn.getImage();
		
		//System.out.println(this.getN()+", "+this.getE()+", "+this.getS()+", "+this.getW());
		
	}
	
}
