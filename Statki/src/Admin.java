
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Admin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final String port = "1234";
	private static Object ip = "0000";
		
	public Admin() {
		super("Logowanie");
		LoginListener listener = new LoginListener(this);
		JPanel gracz = new Gracz(listener);
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
	
	
	public static boolean userValidation(String ip, String port) {
		if(Admin.ip.equals(ip) & Admin.port.equals(port))
			return true;
		else
			return false;
	}
	
}
