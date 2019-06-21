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
	
	public Pole(int row,int col,String value) {
		pole = new JButton();
		
		this.value = value;
		this.row = row;
		this.col = col;
		
		pole.setPreferredSize(new Dimension(100, 100));
		pole.addActionListener(this);
		
		//plansza widoczna od razu (jesli plansza uzytkownika)
		pole.setText(this.value);
		Plansza.buttonPanel.add(pole);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		
		if(source == pole)
			if (this.value != ".") {
				pole.setBackground(Color.CYAN);
				setStan(true);
				Statek.czyZatopiony();
			}
			else {
			pole.setBackground(Color.GRAY);
			pole.setText("P");
			setStatus(true);
			}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean isStan() {
		return isStan();
	}

	public void setStan(boolean stan) {
	}
	
}
