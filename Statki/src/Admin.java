import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Admin{
	
	static int portNumber = 1777;
	private static String ip;
	public static Plansza game;

		
	public Admin(JFrame LogIN) throws UnknownHostException {
		ip=InetAddress.getLocalHost().getHostAddress();

		Gracz gracz = new Gracz(Main.typGracza,ip,portNumber);
		
		LogIN.getContentPane().removeAll();
		LogIN.repaint();
		LogIN.getContentPane().add(gracz);
		LogIN.setPreferredSize(new Dimension(600, 400));
		LogIN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LogIN.pack();
		LogIN.setVisible(true);
				
	}
	
}
