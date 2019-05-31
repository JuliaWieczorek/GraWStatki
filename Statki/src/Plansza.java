import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plansza extends JFrame{
	
	JPanel panel = new JPanel();
	static JPanel buttonPanel;
	static int rows = 10;
	static int columns = 10;
	static int gridSize;
	public static Pole pola[][] = new Pole[10][10];
	public static int[] boatSize = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	private int boat;

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
		
	public Plansza(int gridSize, int height, int width) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 400));
		setVisible(true);
		setTitle("Statki");
		panel = new JPanel();
		panel.setLayout(new GridBagLayout()); 
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(10, 10));
		
		for (rows = 0; rows < gridSize; rows++) {
			for (columns = 0; columns < gridSize; columns++) {
				pola[rows][columns] = new Pole();
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
	}