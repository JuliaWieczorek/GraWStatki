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
	int portNumber = 1777;
	private String ip = "";
	
	private JFrame frame;
	Plansza mojaPlansza;
	Plansza przeciwnikPlansza;
	
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
	    } 
		});}
}
