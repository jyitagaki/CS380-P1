//Joshua Itagaki
//CS 380

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("codebank.xyz", 38001)){
			String user;
			Scanner s = new Scanner(System.in);
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			PrintStream prt = new PrintStream(out);
			InputStreamReader inStream = new InputStreamReader(in);
			BufferedReader buff = new BufferedReader(inStream);
			
			System.out.print("Please enter your name: ");
			user = s.nextLine();
			prt.println(user);
			
			Runnable message = () -> {
				try{
					String outp = buff.readLine();
					System.out.println("Server: " + outp);
					if(!outp.equals("Name in use.")){
						while(true){
							System.out.print("Server: " + buff.readLine());
						}
					} else{
						System.exit(0);
					}
				} catch(IOException e){
					System.out.println(e);
				}
			};
			String input;
			while((input = buff.readLine()) != null){
				System.out.print("Client: ");
				input = buff.readLine();
				prt.println(input);
				if(input.equals("exit")){
					break;
				}
			}
			out.close();
			buff.close();
			System.exit(0);
		}
	}
}
