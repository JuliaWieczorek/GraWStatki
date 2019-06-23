import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;	
	public static String typGracza = "Administrator";
	private static JButton choiceButton;
	static JFrame LogIN=new JFrame("Logowanie");
	
	public static void main(String[] args) {
		
        JPanel choicePanel=new JPanel();
        
        
        LogIN.setPreferredSize(new Dimension(600, 400));
        LogIN.pack();
        LogIN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
	
        String[] typyGracza = new String[] {"Administrator", "Gracz"};
		JComboBox<String> listaGraczy = new JComboBox<>(typyGracza);
        
		choicePanel.add(listaGraczy);
		choiceButton = new JButton("Wybierz");
		choicePanel.add(choiceButton);
		
	
		listaGraczy.addActionListener(new ActionListener() {  
	        public void actionPerformed(ActionEvent e) {       
	        	typGracza =  (String) listaGraczy.getSelectedItem();
	        	
	    } 
		});
		
		choiceButton.addActionListener(new ActionListener() {  
	        @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
	        	choiceButton.disable();
	        	if (typGracza=="Administrator"){
	        		try {
						new Admin(LogIN);
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	        	if (typGracza=="Gracz"){
	        		Gracz gracz = new Gracz(typGracza);
	        		
	        		LogIN.getContentPane().removeAll();
	        		LogIN.repaint();
	        		LogIN.getContentPane().add(gracz);
	        		LogIN.setPreferredSize(new Dimension(600, 400));
	        		LogIN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        		LogIN.pack();
	        		LogIN.setVisible(true);
	        	}
	    } 
		});
		
		LogIN.add(choicePanel);
		LogIN.setVisible(true);
	
	}

}
