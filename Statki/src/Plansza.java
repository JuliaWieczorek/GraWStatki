import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Plansza extends JFrame{
	
	JPanel panel = new JPanel();
	JPanel buttonPanel;
	static int rows = 10;
	static int columns = 10;
	static int gridSize;
	public static JButton button[][] = new JButton[10][10];

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
	
	public Plansza() {
	super( "Statki" );
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(600, 600);
	setLocation(50,50);
	setLayout(new GridLayout(10, 11));
	for(int i=0; i<100; i++)
		add(new JButton(""));

	setVisible(true);
	}
	
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
				button[rows][columns] = new JButton();
				button[rows][columns].setBackground(Color.GRAY); 
				button[rows][columns].setPreferredSize(new Dimension(100, 100));
				
//				button[rows][columns].addActionListener(new TilePressed(rows, columns));
				buttonPanel.add(button[rows][columns]);
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

		//panel.add(gbc);
		setContentPane(panel);
		pack();
	}
	}