import java.io.*;
import java.net.*;

public class Socket1Server {
	public static void main(String[] args) throws Exception{
		Socket1Server Server = new Socket1Server();
		Server.run();
		
	}
	
	public void run() throws Exception{
		
		//Create server socket and choose port number to keep consistent 
		ServerSocket ServSock = new ServerSocket(222);
		Socket sock = ServSock.accept();
		InputStreamReader IR = new InputStreamReader(sock.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String message = BR.readLine();
		System.out.println(message);
		
		// check if valid
		if(message != null){
			PrintStream PS = new PrintStream(sock.getOutputStream());
			PS.println("Message communicated!");
		}
	}
	
	

}
