import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gracz extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField ipField;
	private JTextField portField;
	private JButton loginButton;
	private LoginListener listener;
	
	public String getIP() {
		return ipField.getText();
	}
	
	public String getPort() {
		return portField.getText();
	}
	
		
	public Gracz(LoginListener listener) {
		super();
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.CENTER;
		gridBag.setConstraints(this, constraints);
		setLayout(gridBag);
		this.listener = listener;
		this.listener.setPanel(this);		
		createLogin();
	}
	
	void createLogin() {
		JLabel ip = new JLabel("IP: ");
		JLabel port = new JLabel("Port: ");
		ipField = new JTextField();
		portField = new JTextField();

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2, 2));
		inputPanel.add(ip);
		inputPanel.add(ipField);
		inputPanel.add(port);
		inputPanel.add(portField);
		loginButton = new JButton("Zaloguj");
		loginButton.addActionListener(listener);

		//pomocniczy panel do wyśrodkowania element�w
		JPanel parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(inputPanel, BorderLayout.CENTER);
		parentPanel.add(loginButton, BorderLayout.SOUTH);

		// dodajemy do głównego panelu
		this.add(parentPanel);
	}

}
