import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gracz extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton loginButton;

	private JTextField ipField;
	private JTextField portField;
	
	public String typ="";
	private int portNumber = 1777;
	private String ip = "";
	
	private JFrame frame;
	private Plansza mojaPlansza;
	private Plansza przeciwnikPlansza;
	
	public int strzalX;
	public int strzalY;
	
	
	public String getIP() {
		return ipField.getText();
	}
	
	public String getPort() {
		return portField.getText();
	}
	
	private JFrame getMojaPlansza() {
		return mojaPlansza;
	}

	private void setMojaPlansza(Plansza mojaPlansza) {
		mojaPlansza = mojaPlansza;
	}

	private JFrame getPlansza() {
		return przeciwnikPlansza;
	}

	private void setPlansza(Plansza plansza) {
		przeciwnikPlansza = plansza;
	}

	
	//Gracz ADMIN - serwer	
	public Gracz(String typ, String ip, int port){
		super();
		this.typ=typ;
		this.ip = ip;
		this.portNumber = port;
		
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.CENTER;
		gridBag.setConstraints(this, constraints);
		setLayout(gridBag);
					
		createLogin();
		
		
		
	}
	//Gracz - klient
	public Gracz(String typ) {
		super();
		this.typ=typ;
		
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.CENTER;
		gridBag.setConstraints(this, constraints);
		setLayout(gridBag);

		createLogin();
	}


	public void createLogin() {
		
		JLabel ip = new JLabel("IP: ");
		JLabel port = new JLabel("Port: ");
		ipField = new JTextField();
		portField = new JTextField();
		
		System.out.println(this.typ);
		if (this.typ=="Administrator") {
			ipField.setText(this.ip);
			portField.setText(Integer.toString(this.portNumber));
		}
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		
		inputPanel.setLayout(new GridLayout(2, 2));
		inputPanel.add(ip);
		inputPanel.add(ipField);
		inputPanel.add(port);
		inputPanel.add(portField);
		loginButton = new JButton("Zaloguj");
		
		JPanel parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(inputPanel, BorderLayout.CENTER);
		parentPanel.add(loginButton, BorderLayout.SOUTH);

		this.add(parentPanel);
		
		loginButton.addActionListener(new ActionListener() {  
	        public void actionPerformed(ActionEvent e) {  
	        	System.out.println("Zalogowano");
	        	setPlansza(new Plansza(2, 10, Gracz.this));
				getPlansza();
				setMojaPlansza(new Plansza(1, 10, Gracz.this));
				getMojaPlansza();
				//frame.getContentPane().removeAll();
				//frame.validate();
				

				
	    } 
		});}
	
	public void server() throws Exception {
		
	    ServerSocket serverSocket = new ServerSocket(portNumber);
	    // Wait for the connection
	    System.out.println("Waiting for a connection on IP: " +InetAddress.getLocalHost()+ ", port: " + portNumber);
	    Socket fromClientSocket = serverSocket.accept();
	    System.out.println("Connection Made!");
	    
	    BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
	    
	    // Once connection made create In & Out Streams
	    PrintWriter sender = new PrintWriter(fromClientSocket.getOutputStream(), true);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));
	    
	    Thread senderThread = new Thread(new Sender("Server", sender, keyReader));
	    Thread readerThread = new Thread(new Reader("Client", reader, keyReader));

	    readerThread.setPriority(Thread.MAX_PRIORITY);
	    senderThread.start();
	    readerThread.start();
	}	
	
	public void client() throws NumberFormatException, UnknownHostException, IOException {
			
		// Create Network Socket (Use IP + Port Number)	
		Socket socket = new Socket(InetAddress.getByName(getIP()), Integer.parseInt(getPort()));
			
	    // Create Input Stream and Reader to Read from the keyboard
	    BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
		
	    // Create Input Stream and Reader to Read from Server
	    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    
	    // Create Output Stream and Writer to Write to Server
	    PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
	    
	    
	    Thread senderThread = new Thread(new Sender("Client", sender, keyReader));
	    Thread readerThread = new Thread(new Reader("Server", reader, keyReader));

	    readerThread.setPriority(Thread.MAX_PRIORITY);
	    senderThread.start();
	    readerThread.start();
	    
	  }
}
