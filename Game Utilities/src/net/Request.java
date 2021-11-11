package net;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Hashtable;

public class Request implements Serializable{
	Hashtable <String,Object> data;
	
	public Request(String type,String route) {
		data=new Hashtable<>();
	}
	
	public Request(Hashtable<String,Object> data) {
		this.data=data;
	}
	
	public void set(String s, Object serializable) {
		data.put(s, serializable);
	}
	
	public Object get(String s) {
		return data.get(s);
	}
}
