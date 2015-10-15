import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;


public class BlocLibre extends BlocGraphique {
	
	private String type = "BlocLibre";
	
	public BlocLibre(int i, Niveau niv){
		super(i,niv);
	}

	@Override
	public void actionClic() {

		int[] temp = new int[4];
		for (int i = 0; i<=3;i++){
			temp[i]=this.connexionsEffectives[i];
		}
		for (int i=0;i<=3;i++){
			this.connexionsEffectives[(i+1)%4] = temp[i];
		}
	}

	@Override
	public String getType() {
		return this.type;
	}

}
