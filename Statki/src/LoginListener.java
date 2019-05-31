import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LoginListener implements ActionListener{

	private final JFrame frame;
	private Gracz gracz;

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
					// panel z edytorem html
					JFrame plansza = new Plansza(10, 10, 10);
					// usuwamy panel logowania
					frame.getContentPane().removeAll();
					// dodajemy panel html i odœwie¿amy widok
					frame.add(plansza);
					frame.validate();
				}
			});
		}
	}
}
