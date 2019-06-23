import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Plansza extends JFrame implements ActionListener{
	
	JPanel panel = new JPanel();
	static JPanel buttonPanel;
	static int rows = 10;
	static int columns = 10;
	static int gridSize;
	public static Pole pola[][] = new Pole[10][10];
	public static int[] boatSize = {4,3,3,2,2,2,1,1,1,1};
	//private int boat;

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
	
	private String[][] plansza = new String[10][10];
	private String[][] listaStatkow = new String[10][5];
	private Statek statek;
	
	static int punkty;
	public static JLabel punktacja;
	public static JLabel zatopione;
		
	public Plansza(int kogo, int gridSize, Gracz gracz) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 400));
		setVisible(true);
		if (kogo == 1) setTitle("MojaPlansza");
		else setTitle("Plansza przeciwnika");
		panel = new JPanel();
		panel.setLayout(new GridBagLayout()); 
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(10, 10));
			
		plansza = Generator();
		
		for (rows = 0; rows < gridSize; rows++) {
			for (columns = 0; columns < gridSize; columns++) {
				if( plansza[rows][columns]!=".") {
					pola[rows][columns] = new Maszt(kogo, rows,columns,plansza[rows][columns]);
					pola[rows][columns].setStatus(true);
					pola[rows][columns].setBackground(Color.RED);
					
					}
				else {
					pola[rows][columns] = new Pole(kogo, rows,columns,plansza[rows][columns]);
				}
			}
		}

		//tworzenie Statkow
		for (int i = 0; i < 10; i++) {				
			setStatek(new Statek(listaStatkow[i][0],listaStatkow[i][1]));
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
		
		if (kogo == 1) {
			JButton losujButton = new JButton("Losuj");
			losujButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonPanel.removeAll();
					buttonPanel.repaint();
					buttonPanel.revalidate();
					plansza = Generator();
					for (rows = 0; rows < gridSize; rows++) {
						for (columns = 0; columns < gridSize; columns++) {
							if( plansza[rows][columns]!=".") {
								pola[rows][columns] = new Maszt(kogo, rows, columns, plansza[rows][columns]);
								pola[rows][columns].setStatus(true);
								}
							else {
								pola[rows][columns] = new Pole(kogo, rows, columns, plansza[rows][columns]);
								}}}

							//tworzenie Statkow
							for (int i = 0; i < 10; i++) {				
								setStatek(new Statek(listaStatkow[i][0],listaStatkow[i][1]));
							}		
				}});
			
			JButton zatwierdzButton = new JButton("Zatwierdz ustawienie");
			zatwierdzButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					losujButton.setEnabled(false);
					
					blokujPlansze();
					
					
					if (gracz.typ=="Administrator") {
						System.out.println("Admin czeka");
						try {
							gracz.server();
						} catch (Exception b) {
							// TODO Auto-generated catch block
							b.printStackTrace();
						}
					}
					else
						try {
							System.out.println("Klient czeka");
							gracz.client();
						} catch (Exception b) {
							// TODO Auto-generated catch block
							b.printStackTrace();
						}
			}});
			
			
			JPanel inputPanel = new JPanel();
			inputPanel.setLayout(new GridLayout(2, 2));
			inputPanel.add(losujButton);
			inputPanel.add(zatwierdzButton);
			panel.add(inputPanel, gbc);
		}
		
		else {
			JLabel punkt = new JLabel("Punkty: ");
			punktacja = new JLabel(String.valueOf(Plansza.getPunkty()));
			zatopione = new JLabel(" ");
			JPanel punktPanel = new JPanel();
			punktPanel.setLayout(new GridLayout(2, 2));
			punktPanel.add(punkt);
			punktPanel.add(punktacja);
			punktPanel.add(zatopione);
			panel.add(punktPanel, gbc);
		}
		
		setContentPane(panel);
		pack();
			
	}
	
	public String[][] Generator() {
	
		
		for (int i = 0; i < plansza.length; i++) {
		    for (int j = 0; j < plansza[i].length; j++) {
		        plansza[i][j] = ".";
		    }
		}
		int nrStatku = 0;
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
							listaStatkow[nrStatku][0]=Integer.toString(boat);
							listaStatkow[nrStatku][1]="";
							for (int i=x_min; i<x_max;i++) {
								for (int j=y_min; j<y_max;j++) {
									plansza[i][j]="/";
								}
							}
							for (int i=0; i<licznik; i++) {
								plansza[x+i][y]=Integer.toString(boat);
								listaStatkow[nrStatku][1]+=Integer.toString(x+i)+Integer.toString(y)+",";
								}
							done=false;
							nrStatku+=1;
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
							listaStatkow[nrStatku][0]=Integer.toString(boat);
							listaStatkow[nrStatku][1]="";

							for (int i=x_min; i<x_max;i++) {
								for (int j=y_min; j<y_max;j++) {
									plansza[i][j]="/";
								}
							}
							for (int i=0; i<licznik; i++) {
								plansza[x][y+i]=Integer.toString(boat);
								listaStatkow[nrStatku][1]+=Integer.toString(x)+Integer.toString(y+i)+",";
								}						
							done=false;
							nrStatku+=1;
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
	
	public static void blokujPlansze() {
		Component[] com = buttonPanel.getComponents();
		for (int a = 0; a < com.length; a++) {
			com[a].setEnabled(false);}
	}
	
	public static void odblokujPlansze() {
		Component[] com = buttonPanel.getComponents();
		for (int a = 0; a < com.length; a++) {
			com[a].setEnabled(true);}
	}

	public Statek getStatek() {
		return statek;
	}

	public void setStatek(Statek statek) {
		this.statek = statek;
	}

	public static int getPunkty() {
		return punkty;
	}

	public void setPunkty(int punkty) {
		punkty = getPunkty() + Plansza.punkty;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		System.out.println(source);
		// TODO Auto-generated method stub
		
	}
	
	}