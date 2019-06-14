import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plansza extends JFrame{
	
	JPanel panel = new JPanel();
	static JPanel buttonPanel;
	static int rows = 10;
	static int columns = 10;
	static int gridSize;
	public static Pole pola[][] = new Pole[10][10];
	public static int[] boatSize = {4,3,3,2,2,2,1,1,1,};
	private int boat;

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
	
	private String[][] plansza = new String[10][10];
		
	public Plansza(int gridSize, int height, int width) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 400));
		setVisible(true);
		setTitle("Statki");
		panel = new JPanel();
		panel.setLayout(new GridBagLayout()); 
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(10, 10));
		
		plansza = Generator();
		
		for (rows = 0; rows < gridSize; rows++) {
			for (columns = 0; columns < gridSize; columns++) {
				pola[rows][columns] = new Pole(rows,columns,plansza[rows][columns]);
			}
		}
		
		
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.65;

		panel.add(buttonPanel, gbc);

		gbc.weighty = 0.05;
		gbc.gridy = 1;

		setContentPane(panel);
		pack();
		
		// nadajemy wielkosc statku
		for (int i = 0; i < boatSize.length; i++) {
			boat = boatSize[i];
			Statek statek = new Statek();
			statek.setWielkosc(boat);
		}	
	}
	public String[][] Generator() {
	
		
		for (int i = 0; i < plansza.length; i++) {
		    for (int j = 0; j < plansza[i].length; j++) {
		        plansza[i][j] = ".";
		    }
		}
		
		for (int boat : boatSize) {
			Boolean done = true;
			//System.out.println(boat);
			while (done) {
				int licznik = 0;
				
				int x = new Random().nextInt(10);
				int y = new Random().nextInt(10);
				
				if (plansza[x][y] == ".") {
					int kierunek = new Random().nextInt(2);
					
					if (kierunek == 0){
						int x_min = x-1;
						int x_max = x+boat+1;
						int y_min = y-1;
						int y_max = y+2;
						
						if(x_min<0) {x_min=0;}
						if(x_max>=10) {x_max=10;}
						if(y_min<0) {y_min=0;}
						if(y_max>=10) {y_max=10;}
					
						
						//spr czy puste
						for (int i=0; i<boat+1; i++) {
							if (x+i<10) {
								if( plansza[x+i][y]==".") {
									licznik=licznik+1;
								}	
							}	
						}
						
						if (licznik==boat) {
							for (int i=x_min; i<x_max;i++) {
								for (int j=y_min; j<y_max;j++) {
									plansza[i][j]="/";
								}
							}
							for (int i=0; i<licznik; i++) {
								plansza[x+i][y]=Integer.toString(boat);
								}
							done=false;
							}
						else {done=true;}
								
						}
						
					if (kierunek == 1){
						int x_min = x-1;
						int x_max = x+2;
						int y_min = y-1;
						int y_max = y+boat+1;
						
						if(x_min<0) {x_min=0;}
						if(x_max>=10) {x_max=10;}
						if(y_min<0) {y_min=0;}
						if(y_max>=10) {y_max=10;}
					
						
						//spr czy puste
						for (int i=0; i<boat+1; i++) {
							if (y+i<10) {
								if(plansza[x][y+i]==".") {
									licznik=licznik+1;
								}	
							}	
						}
						
						
						if (licznik==boat) {
							for (int i=x_min; i<x_max;i++) {
								for (int j=y_min; j<y_max;j++) {
									plansza[i][j]="/";
								}
							}
							for (int i=0; i<licznik; i++) {
								plansza[x][y+i]=Integer.toString(boat);
								}
							done=false;
							}
						else {done=true;}
													
					}
				}
					
			}
		}
		for (int i=0; i<10;i++) {
			for (int j=0; j<10;j++) {
				if (plansza[i][j]=="/") {plansza[i][j]=".";}
			}
		}
		
		
		
		return plansza;
		
	}

	
	
	}