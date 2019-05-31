
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Admin extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Gracz loginPanel;
	private static final String port = "1234";
	private static Object ip = "0000";
	//private Admin admin = new Admin();
	
	public void setPanel(Gracz loginPanel) {
		this.loginPanel = loginPanel;
	}
	
	public void loginListener(JFrame frame) {
		this.frame = frame;
	}
	
	public Admin() {
		super("Logowanie");
		loginListener(this);
		JPanel gracz = new Gracz();
		add(gracz);
		/*nadaæ przycisk z ActionFrame
		 * tutaj bêdzie 10x10 pól :)
		 Pole pole = new Pole();
		 add(pole);
		 */
		setPreferredSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent event) {
		String ip = loginPanel.getIP();
		String port = loginPanel.getPort();
		if (userValidation(ip, port)) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					// panel z edytorem html
					JPanel Plansza = new Plansza();
					// usuwamy panel logowania
					frame.getContentPane().removeAll();
					// dodajemy panel html i odœwie¿amy widok
					frame.add(Plansza);
					frame.validate();
				}
			});
		}
	}
	
	public static boolean userValidation(String ip, String port) {
		if(Admin.ip.equals(ip) & Admin.port.equals(port))
			return true;
		else
			return false;
	}
	
}
