package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

import net.Request;

public abstract class Controller extends Thread{
	ObjectOutputStream out;
	Socket s;
	CopyOnWriteArrayList<Request> requests;
	public Controller(Socket s,ObjectOutputStream out) {
		this.out=out;
		this.s=s;
		//this.screen=screen;
		requests=new CopyOnWriteArrayList<>();
		start();
	}
	
	public void queueProcessRequest(Request r) {
		requests.add(r);
	}
	
	public final void run() {
		//start reciever
		ObjectInputStream in=null;
		try {
			in=new ObjectInputStream(s.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.err.println("WARNING: Reciever failed, nothing can be recieved from the server henceforth.");
		}
		Reciever reciever=new Reciever(s,in);
		reciever.start();
		//process requests
		while (s.isConnected()) {
			for (Request r:requests) {
				String type=(String) r.get("type");
				String route=(String) r.get("route");
				switch (type){
					case "get":
						this.get(r,type);
					break;
					case "post":
						try {
							out.writeObject(r);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					break;
				}
			}
		}
		
	}
	
	public abstract void get(Request r,String type);
	
	private class Reciever extends Thread{
		
		ObjectInputStream in;
		
		public Reciever(Socket s,ObjectInputStream in) {
			this.in=in;
		}
		
		public void run() {
			while (s.isConnected()) {
				Request r;
				try {
					r = (Request) in.readObject();
					queueProcessRequest(r);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
}
