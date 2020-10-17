package br.ufc.crateus.exec;

import br.ufc.crateus.server.Server;

public class Main {

	public static void main(String[] args) throws Exception {
		int portNumber = 3333;
		Server server = new Server(portNumber);
		server.start();
	}

}
