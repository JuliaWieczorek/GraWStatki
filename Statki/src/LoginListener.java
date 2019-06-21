import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LoginListener implements ActionListener{

	private final JFrame frame;
	private Gracz gracz;
	private JFrame mojaPlansza;
	private JFrame Plansza;

	public void setPanel(Gracz gracz) {
		this.gracz = gracz;
	}
	
	public LoginListener(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String ip = gracz.getIP();
		String port = gracz.getPort();
		if (Admin.userValidation(ip, port)) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					setPlansza(new Plansza(2, 10));
					getPlansza();
					setMojaPlansza(new Plansza(1, 10));
					getMojaPlansza();
					frame.getContentPane().removeAll();
					frame.validate();
				}
			});
		}
	}

	private JFrame getMojaPlansza() {
		return mojaPlansza;
	}

	private void setMojaPlansza(JFrame mojaPlansza) {
		this.mojaPlansza = mojaPlansza;
	}

	private JFrame getPlansza() {
		return Plansza;
	}

	private void setPlansza(JFrame plansza) {
		Plansza = plansza;
	}

		
}
