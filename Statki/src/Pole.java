import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Pole extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean status;
	private JButton pole;
	int row;
	int col;
	String value;
	
		
	public Pole(int row,int col,String plansza) {
		pole = new JButton();
		
		this.value = plansza;
		this.row = row;
		this.col = col;
		
		pole.setPreferredSize(new Dimension(100, 100));
		pole.addActionListener(this);
		Plansza.buttonPanel.add(pole);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		
		if(source == pole)
			pole.setBackground(Color.GRAY);
			pole.setText(this.value);
			//System.out.println(this.row);  
			//System.out.println(this.col);
			setStatus(true);
			Statek statek = new Statek();
			statek.setPolozenie(pole);
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
