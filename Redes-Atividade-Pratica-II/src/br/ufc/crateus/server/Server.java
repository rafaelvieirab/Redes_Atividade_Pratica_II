package br.ufc.crateus.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private int portNumber;
	private ServerSocket socketServer;
	
	public Server(int portNumber) throws IOException {
		this.portNumber = portNumber;
	}
	
	public void start() throws Exception {
		socketServer = new ServerSocket(portNumber);
		System.out.println("Servidor está ativo");
		
		while(true) {
			Socket socketClient = socketServer.accept();
			Handle handle = new Handle(socketClient);
			Thread thread = new Thread(handle);
			thread.start();
		}
	}
}
