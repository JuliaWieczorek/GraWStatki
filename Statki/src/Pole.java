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
	
	public Pole(int kogo, int row, int col, String value) {
		pole = new JButton();
		
		this.value = value;
		this.row = row;
		this.col = col;
		
		pole.setPreferredSize(new Dimension(100, 100));
		pole.addActionListener(this);
				
		if (kogo == 1){
			pole.setText(this.value);
			Plansza.buttonPanel.add(pole);
			}
		else Plansza.buttonPanel.add(pole);
		}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String strzal = (this.row+" "+this.col);
		String odpowiedz = (this.value);

		
		if(source == pole)
			// wyslij this.row i  this.col do przeciwnika.
			if (this.value != ".") {
				//pole.setBackground(Color.CYAN);
				setStan(true);
				Statek statek = getStatek();
				statek.czyZatopiony();
				
				//aktualizacja punktacji
				setPunkty(Integer.parseInt(this.value));
				Plansza.punktacja.removeAll();
				Plansza.punktacja.revalidate();
				String pkt = Integer.toString(Plansza.getPunkty());
				Plansza.punktacja.setText(pkt);
				}
			else {
			//pole.setBackground(Color.GRAY);
			//pole.setText(" ");
			setStatus(true);
			}
	}
		
	public void setPunkty(int punkty) {
		Plansza.punkty = Plansza.getPunkty() + punkty;
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

	public Statek getStatek() {
		return getStatek();
	}

	public void setStatek(Statek statek) {
	}
}
