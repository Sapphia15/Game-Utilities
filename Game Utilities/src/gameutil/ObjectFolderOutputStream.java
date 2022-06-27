package gameutil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;

public class ObjectFolderOutputStream extends OutputStream{
	OutputStream o;
	Hashtable<Integer, byte[]> objectBuffer;
	
	public ObjectFolderOutputStream(OutputStream o) {
		this.o=o;
	}
	
	

	
	
	public void bufferObject(int address,Object o) {
		ByteArrayOutputStream bout=new ByteArrayOutputStream();
		try {
			ObjectOutputStream oout=new ObjectOutputStream(bout);
			oout.writeObject(o);
			objectBuffer.put(address,bout.toByteArray());
			oout.close();
			bout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeFolder() throws IOException {
		Hashtable<Integer,java.awt.Point> index=new Hashtable<>();
		int off=0;
		for (int address : objectBuffer.keySet()) {
			byte[] b=objectBuffer.get(address);
			index.put(address, new java.awt.Point(off,b.length));
			off+=b.length;
		}
		ByteArrayOutputStream bout=new ByteArrayOutputStream();
		ObjectOutputStream oout=new ObjectOutputStream(bout);
		oout.writeObject(index);
		byte[] indexBytes=bout.toByteArray();
		o.write(indexBytes);
		o.write(indexBytes.length);
		
		for (int address : objectBuffer.keySet()) {
			byte[] b=objectBuffer.get(address);
			o.write(b);	
		}
		
	}
	
	@Override
	public void write(int b) throws IOException {
		o.write(b);
	}

}
