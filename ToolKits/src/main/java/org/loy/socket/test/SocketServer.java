package org.loy.socket.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void  main(String args[]) {
		try {
			ServerSocket server = null;
			try {
				server = new ServerSocket(25250);
			} catch (Throwable e) {
				System.out.println("Can't listen to port 25250");
				e.printStackTrace();
			}
			
			Socket socket = null;
			try{
				socket = server.accept();
			} catch (Throwable e) {
				System.out.println("Error."+e);
			}

			System.out.println("Socket server is running~");
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			
			String clientInput = is.readLine();
			System.out.println("Client 1st request : " + clientInput);
			/*
			String line = null;
			do {
				line = sin.readLine();
				System.out.println("I response: " + line);
				os.println(line);
				os.flush();
				System.out.println("Client input: " + is.readLine());
				line = sin.readLine();
			} while (!"bye".equals(line));
			*/
			
			String line=sin.readLine();
			while(!line.equals("bye")){
				os.println(line);
				os.flush();
				System.out.println("Server:"+line);
				System.out.println("Client:"+is.readLine());
				line=sin.readLine();
			}
			
			sin.close();
			os.close();
			is.close();
			socket.close();
			server.close();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
