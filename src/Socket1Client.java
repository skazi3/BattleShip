import java.io.*;
import java.net.*;

public class Socket1Client {
	public static void main(String[] args) throws Exception{
		Socket1Client client = new Socket1Client();
		client.run();
	}
	
	public void run() throws Exception{
		
		//Keep port consistent with server 
		Socket sock = new Socket("Local ", 222);
		PrintStream PS = new PrintStream (sock.getOutputStream());
		PS.println("Hi from the Client to the Server!");
		
		InputStreamReader IR = new InputStreamReader(sock.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String message = BR.readLine();
		System.out.println(message);
		
		
	}

}
