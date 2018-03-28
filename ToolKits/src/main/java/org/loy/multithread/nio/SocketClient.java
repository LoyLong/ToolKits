package org.loy.multithread.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

	public static void main(String args[]) {
		
		try {
            Socket socket = new Socket("133.13.167.77", 1234);
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			/*
			String readline = null;
			do {
				readline = sin.readLine();
				System.out.println("I input: " + readline);
				os.println(readline);
				os.flush();
				System.out.println("Server response : " + is.readLine());
				
			} while (!readline.equals("bye"));
			*/

			String readline=sin.readLine();

			while(!readline.equals("bye")){
				os.println(readline);
				os.flush();
				System.out.println("Client:"+readline);
				System.out.println("Server:"+is.readLine());
				readline=sin.readLine();
			}
			os.close();
			is.close();
			sin.close();
			socket.close();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
}
