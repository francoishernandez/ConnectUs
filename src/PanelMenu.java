import javax.swing.*;

import java.awt.event.*;

public class PanelMenu extends JMenuBar{

	private Partie partie;

	public PanelMenu(Partie p){
		
		this.partie = p;

		JMenu niveau = new JMenu("Niveaux monotypes");
		this.add(niveau);
		
		
		JMenuItem niveau1 = new JMenuItem("Niveau 1");
		niveau1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(1);
				partie.setBouton(false);
				//System.out.println("Clic Niveau 1");
			}
		});
		niveau.add(niveau1);
		
		JMenuItem niveau2 = new JMenuItem("Niveau 2");
		niveau2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(2);
				partie.setBouton(false);
				//System.out.println("Clic Niveau 2");
			}
		});
		niveau.add(niveau2);
		
		JMenuItem niveau3 = new JMenuItem("Niveau 3");
		niveau3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(3);
				partie.setBouton(false);
				//System.out.println("Clic Niveau 3");
			}
		});
		niveau.add(niveau3);
		
		JMenuItem niveau4 = new JMenuItem("Niveau 4");
		niveau4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(4);
				partie.setBouton(false);
				//System.out.println("Clic Niveau 4");
			}
		});
		niveau.add(niveau4);
		
		JMenuItem niveau5 = new JMenuItem("Niveau 5");
		niveau5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(5);
				partie.setBouton(false);
				//System.out.println("Clic Niveau 5");
			}
		});
		niveau.add(niveau5);
		
		JMenu niveauBis = new JMenu("Niveaux polytypes");
		this.add(niveauBis);
		
		JMenuItem niveau6 = new JMenuItem("Niveau 6");
		niveau6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(6);
				partie.setBouton(false);
				//System.out.println("Clic Niveau 6");
			}
		});
		niveauBis.add(niveau6);
		
		JMenuItem niveau7 = new JMenuItem("Niveau 7");
		niveau7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(7);
				partie.setBouton(false);
				//System.out.println("Clic Niveau 7");
			}
		});
		niveauBis.add(niveau7);
		
		JMenuItem niveau8 = new JMenuItem("Niveau 8");
		niveau8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(8);
				partie.setBouton(false);
				//System.out.println("Clic Niveau 8");
			}
		});
		niveauBis.add(niveau8);
		/*
		JMenuItem niveau9 = new JMenuItem("Niveau 9");
		niveau9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(9);
				partie.setBouton(false);
				System.out.println("Clic Niveau 9");
			}
		});
		niveauBis.add(niveau9);
		
		JMenuItem niveau10 = new JMenuItem("Niveau 10");
		niveau10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				partie.setNumeroNiveauCourant(10);
				partie.setBouton(false);
				System.out.println("Clic Niveau 10");
			}
		});
		niveauBis.add(niveau10);
		 */
	}

	public void setMenu(JFrame f){
		f.setJMenuBar(this);

	}

	class actionNiveau implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Niveau n = new Niveau(partie);
		}
	}
}
