package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

import net.Request;

public abstract class Controller extends Thread{
	protected ObjectOutputStream out;
	protected Socket s;
	protected CopyOnWriteArrayList<Request> requests;
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
		boolean connected=true;
		while (!s.isClosed()&&connected) {
			for (Request r:requests) {
				String type=(String) r.get("type");
				String route=(String) r.get("route");
				switch (type){
					case "get":
						this.get(r,route);
					break;
					case "post":
						try {
							r.set("type","get");
							out.writeObject(r);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							disconnect();
							connected=false;
						}
						
					break;
				}
				requests.remove(r);
			}
			
		}
		
		
	}
	
	public abstract void get(Request r,String route);
	
	private class Reciever extends Thread{
		
		ObjectInputStream in;
		
		public Reciever(Socket s,ObjectInputStream in) {
			this.in=in;
		}
		
		public void run() {
			while ((!s.isInputShutdown()&&!s.isClosed())) {
				Request r;
				try {
					r = (Request) in.readObject();
					queueProcessRequest(r);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					onDisconnect();
					break;
				}
				
			}
		}
		
		
	}
	
	public void disconnect() {
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onDisconnect() {
		
	}
}
