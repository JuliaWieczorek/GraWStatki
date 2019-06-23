
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Sender implements Runnable  {

	// Create Output Stream and Writer to Write to Server
    PrintWriter writer;
    BufferedReader keyRead;
    String user;
    
	public Sender(String user, PrintWriter writer, BufferedReader keyRead) {
		super();
		this.user = user;
		this.writer = writer;
		this.keyRead = keyRead;
	}
	
	
	
	
	
	@Override
	synchronized public void run() {
		String message;
		try {
			while((message = keyRead.readLine()) != null){
	        
	        writer.println(message);       // send
	        writer.flush();                // flush the data
	        
	        if (message.equals("Bye"))
					break;

	    }
		} catch (Exception e){
			
		}
		
		try {
			keyRead.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(user + " Disconnected! Bye!");
	}
}
