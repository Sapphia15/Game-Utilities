package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public abstract class Server extends Thread{
	
	ArrayList<Socket> clients=new ArrayList<>();
	ServerSocket server;
	int clientCap=-1;
	public Server(ServerSocket s,int clientCap) {
		server=s;
		this.clientCap=clientCap;
		start();
	}
	
	public final void run() {
		//get clients
		while (clientCap<0 || clients.size()<clientCap) {
			try {
				Socket client=server.accept();
				clients.add(client);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public abstract void prepareNewClient(Socket s);
	
	
}
