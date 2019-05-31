import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Pole extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton pole;
	
	public Pole() {
		pole = new JButton();
		pole.addActionListener(this);
		setLayout(new FlowLayout());
		add(pole);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source == pole)
			pole.setBackground(Color.red);	
	}
	
}
