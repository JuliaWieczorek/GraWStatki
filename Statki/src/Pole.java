import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
				punktuj();
				}
			else {
			setStatus(true);
			}
	}
	
	public void punktuj() {
		setPunkty(Integer.parseInt(this.value));
		Plansza.punktacja.removeAll();
		Plansza.punktacja.revalidate();
		String pkt = Integer.toString(Plansza.getPunkty());
		Plansza.punktacja.setText(pkt);}
		
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

	    ServerSocket serverSocket = new ServerSocket(gracz.portNumber);
	    // Wait for the connection
	    Socket fromClientSocket = serverSocket.accept();
	    System.out.println("Connection Made!");
	    
	   //Strzelaj - wysylanie strzalu
        //pobierz row i col wcisnietego przycisku
    	//blokuj plansze przeciwnika
    	//wyslij row i col do przeciwnika   
        ObjectOutputStream oos = new ObjectOutputStream(fromClientSocket.getOutputStream());
	    
	    String strzal = (this.row+" "+this.col);
	    //gracz.przeciwnikPlansza.blokujPlansze();
	    System.out.println("moj strza³:"+ strzal);
	    oos.writeObject(strzal);
	      
	    //Czekaj na odpowiedz
        ObjectInputStream ois = new ObjectInputStream(fromClientSocket.getInputStream());
        String odp = (String) ois.readObject();
	    System.out.println("odp:"+ odp);
	    
	    //Zaznacz odpowiedz na planszy
    	//zmien kolor na Cyan-trafiony lub Grey-pud³o
    	
    	if (odp.equals("pud³o")) {
    		pole.setBackground(Color.GRAY);
    		pole.setText("P");
    	}
    	else{
    		System.out.println("Jetsem tu");
    		pole.setBackground(Color.CYAN);
    		pole.setText("X");
    	}
    	
	    //Czekaj na strza³ przecwinika
	    String strzal2 = null;
		try {
			strzal2 = (String) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Strzal przecwinika: " + strzal2);

        int row = Character.getNumericValue(strzal2.charAt(0));
        int col = Character.getNumericValue(strzal2.charAt(2));
        
        //Odpowiedz na strza³
    	//przekaz przeciwnikowi czy trafil czy spud³owa³
        String odp2 = "blad";
        if(gracz.mojaPlansza.pola[row][col].value==".") {
        	gracz.mojaPlansza.pola[row][col].setBackground(Color.GRAY);
        	odp2 = "pud³o";
        	System.out.println(odp2);
        }
        else {
        	gracz.mojaPlansza.pola[row][col].setBackground(Color.RED);
        	odp2 = "trafiony";
        	System.out.println(odp2);
        }
    
		oos.writeObject(odp2); 
		
	   
	    
        //close resources
        ois.close();
        oos.close();
        fromClientSocket.close();
        //terminate the server if client sends exit request
        //if(message.equalsIgnoreCase("exit")) 
        
        System.out.println("Koniec tury");
        //close the ServerSocket object
        serverSocket.close();
    }
    
	public void client() throws NumberFormatException, UnknownHostException, IOException, ClassNotFoundException {
       
		 Socket socket = new Socket(InetAddress.getByName(gracz.getIP()), Integer.parseInt(gracz.getPort()));
		 ObjectOutputStream oos = null;
	     ObjectInputStream ois = null;  

	     
	    //Odpowiedz na strza³
 		//przekaz przeciwnikowi czy trafil czy spud³owa³
		 ois = new ObjectInputStream(socket.getInputStream());
	       String strzal = null;
			try {
				strzal = (String) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Strzal przecwinika: " + strzal);

	        int row = Character.getNumericValue(strzal.charAt(0));
	        int col = Character.getNumericValue(strzal.charAt(2));
	        
	        String odp = "blad";
	        if(gracz.mojaPlansza.pola[row][col].value==".") {
	        	odp = "pud³o";
	        	System.out.println(odp);
	        }
	        else {
	        	odp = "trafiony";
	        	System.out.println(odp);
	        }
	        
            oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(odp);            
            
			//Strzelaj - wysylanie strzalu
	        //pobierz row i col wcisnietego przycisku
	    	//blokuj plansze przeciwnika
	    	//wyslij row i col do przeciwnika
			String strzal2 = (this.row+" "+this.col);
		    gracz.przeciwnikPlansza.blokujPlansze();
		    System.out.println("moj strza³:"+ strzal2);
		    oos.writeObject(strzal2);
            
			//Czekaj na odpowiedz
		    String odp2 = (String) ois.readObject();
		    System.out.println("odp:"+ odp2);
			
			//Zaznacz odpowiedz na planszy
    		//zmien kolor na Cyan-trafiony lub Grey-pud³o
		    if (odp2.equals("pud³o")) {
	    		pole.setBackground(Color.GRAY);
	    		pole.setText("P");
	    	}
	    	else{
	    		System.out.println("Jetsem tu");
	    		pole.setBackground(Color.CYAN);
	    		pole.setText("X");
	  	    	}
	    	
    
		    //Czekaj na strza³ przecwinika
	        System.out.println("Koniec tury");

           
            //close resources
            ois.close();
            oos.close();
	}    
	  }

