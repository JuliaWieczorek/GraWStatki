import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Pole extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean status;
	private JButton pole;
	int row;
	int col;
	String value;
	Gracz gracz;
	
	
	public Pole(int kogo, int row, int col, String value, Gracz gracz) {
		
		pole = new JButton();
		
		this.value = value;
		this.row = row;
		this.col = col;
		this.gracz = gracz;
		
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
		
		String odpowiedz = (this.value);

		
		if (gracz.typ=="Administrator") {
			System.out.println("Admin czeka");
			try {
				server();
				System.out.println(this.value);
			} catch (Exception b) {
				// TODO Auto-generated catch block
				b.printStackTrace();
			}
		}
		else
			try {
				System.out.println("Klient czeka");
				client();
			} catch (Exception b) {
				// TODO Auto-generated catch block
				b.printStackTrace();
			}
		

		
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
	
	
public void server() throws Exception {
	 	DataInputStream in  =  null;
	 	DataOutputStream out  =  null; 
	 	
	    ServerSocket serverSocket = new ServerSocket(gracz.portNumber);
	    // Wait for the connection
	    Socket fromClientSocket = serverSocket.accept();
	    System.out.println("Connection Made!");
	    
	    
	    in = new DataInputStream( 
                new BufferedInputStream(fromClientSocket.getInputStream()));
	    
	    //Strzelaj - wysylanie strzalu
        //pobierz row i col wcisnietego przycisku
    	//blokuj plansze przeciwnika
    	//wyslij row i col do przeciwnika   
	    String strzal = (this.row+" "+this.col);
	    gracz.przeciwnikPlansza.blokujPlansze();
	    out.writeUTF(strzal);
	    
	    System.out.println("moj strza³:"+ strzal);
	    
	    String odp = in.readUTF();
	    
	    
	    
 
        System.out.println("Closing connection"); 

        // close connection 
        fromClientSocket.close(); 
        in.close(); 
    
     

	    
	    
	    
	    
	   /* 
	    while (true){
	    	
	    
	    	int[] strzal = new int[2];
			strzal[0]=0;
			strzal[1]=0;
			
			System.out.println(strzal[0]+" "+strzal[1]);
			
			gracz.przeciwnikPlansza.blokujPlansze();
			
			break;
			
			
			
	    }*/
	    
	    
	    //Czekaj na odpowiedz
	    
	    //Zaznacz odpowiedz na planszy
	    	//zmien kolor na Cyan-trafiony lub Grey-pud³o
	    	
	    
	    //Czekaj na strza³ przecwinika
	    	
	    
	    //Odpowiedz na strza³
	    	//przekaz przeciwnikowi czy trafil czy spud³owa³
	    
	        
	  
	}	
	
	public void client() throws NumberFormatException, UnknownHostException, IOException {
		DataInputStream  input   = null; 
		DataOutputStream out     = null; 
		
		// Create Network Socket (Use IP + Port Number)	
		Socket socket = new Socket(InetAddress.getByName(gracz.getIP()), Integer.parseInt(gracz.getPort()));
		
		in  =  new BufferedInputStream(socket.getInputStream()));
		out    = new DataOutputStream(socket.getOutputStream()); 

		String strzal = in.readUTF();
	    System.out.println("Strza³ przeciwnika:"+ strzal);
		
		
		//Odpowiedz na strza³
    		//przekaz przeciwnikowi czy trafil czy spud³owa³
		
		//Strzelaj - wysylanie strzalu
	        //pobierz row i col wcisnietego przycisku
	    	//blokuj plansze przeciwnika
	    	//wyslij row i col do przeciwnika
    
    
		//Czekaj na odpowiedz
    
		//Zaznacz odpowiedz na planszy
    		//zmien kolor na Cyan-trafiony lub Grey-pud³o
    	
    
		//Czekaj na strza³ przecwinika
    	
	    
	  }
}
