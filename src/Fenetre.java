import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class Fenetre extends JFrame {

	public Fenetre() throws IOException{

		this.gui();

	}

	public void gui() throws IOException{

		this.setTitle("Connect Us");
		Image icone = Toolkit.getDefaultToolkit().getImage("Blocs/Bloc.png");
		this.setIconImage(icone);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600, 622));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();

	}

}
