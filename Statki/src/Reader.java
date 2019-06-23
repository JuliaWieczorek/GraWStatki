import java.io.BufferedReader;
import java.io.IOException;


public class Reader implements Runnable {

	// Create Input Stream and Reader to Read from Server
	BufferedReader reader;
	BufferedReader keyRead;
	String user;

	public Reader(String user, BufferedReader reader, BufferedReader keyRead) {
		super();
		this.user = user;
		this.reader = reader;
		this.keyRead = keyRead;
	}

	@Override
	synchronized public void run() {
		String message;
		try {
			while ((message = reader.readLine()) != null) {
				System.out.println(user + ": " + message);

				if (message.equals("Bye")) 
					break;
				

			}
		} catch (Exception e) {
			
		}
		
		try {
			reader.close();
			keyRead.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(user + " Disconnected! Bye!");
	}
}
