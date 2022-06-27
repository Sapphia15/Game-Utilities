package gameutil;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;

public class ObjectFolderInputStream extends InputStream{
	
	InputStream s;
	Hashtable<Integer, java.awt.Point> addresses;
	int indexLength;
	
	public ObjectFolderInputStream(InputStream s) throws ClassNotFoundException, IOException {
		ObjectInputStream i;
		i = new ObjectInputStream(s);
		try {
			addresses=(Hashtable<Integer, Point>) i.readObject();
			indexLength=s.read();
		} catch (IOException e) {
			throw new IOException("Could not read hashtable of addresses and lengths");
		}
		i.close();
	}
	
	/**Reads an object at the specified address
	 * 
	 * @param address
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object readObject(int address) throws IOException, ClassNotFoundException {
			java.awt.Point p=addresses.get(address);
			byte[] bytes=new byte[p.y];
			s.read(bytes, indexLength+p.x,p.y);
			ObjectInputStream oin=new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object o=oin.readObject();
			oin.close();
			return o;
	}
	
	public void close() throws IOException {
		super.close();
		s.close();
	}

	/**Returns the whatever the read of the dependent input stream was
	 * 
	 */
	@Override
	public int read() throws IOException {
		return s.read();
	}
}
