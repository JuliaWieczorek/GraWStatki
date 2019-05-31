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
		
	public Pole() {
		pole = new JButton();
		pole.setPreferredSize(new Dimension(100, 100));
		pole.addActionListener(this);
		Plansza.buttonPanel.add(pole);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source == pole)
			pole.setBackground(Color.GRAY);
			setStatus(true);
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
