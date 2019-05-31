import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Admin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final String port = "1234";
	private static Object ip = "0000";
	public static Plansza game;

		
	public Admin() {
		super("Logowanie");
		LoginListener listener = new LoginListener(this);
		JPanel gracz = new Gracz(listener);
		add(gracz);
		setPreferredSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
		/*game = new Plansza(10, 600, 600);
		game.setSize(600, 800);
		game.setVisible(false);*/

	}
	
	/*
	 * TODO uzyskaæ port i ip (ip pobieram, z portem gorzej)
	 * public int getPort(SocketAddress address) {
	    return ((InetSocketAddress) address).getPort();
	}
	
	
	public int getPort() {
		return getLocalPort();
	}
	
	public String getIP() {
		return getLocalAddr();
	}
	public static void getIP(String[] args) {  
	    try {  
	          InetAddress ia = InetAddress.getLocalHost();  
	          String str = ia.getHostAddress();
	          System.out.println(str);  
	        } catch (Exception e) {  
	          e.printStackTrace();  
	        }  
	      }  
	*/
	public static boolean userValidation(String ip, String port) {
		if(Admin.ip.equals(ip) & Admin.port.equals(port))
			return true;
		else
			return false;
	}
	
}
