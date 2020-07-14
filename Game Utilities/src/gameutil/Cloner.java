package gameutil;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public final class Cloner {
	
	private Cloner() {
		
	}
	
	/**Object must be serializable
	 * 
	 * @param o
	 */
	public static Object Clone(Object o) {
		CloneOutputStream out=new CloneOutputStream();
		try {
			ObjectOutputStream oout=new ObjectOutputStream(out);
			oout.writeObject(o);
			CloneInputStream in=out.toInputStream();
			ObjectInputStream oin=new ObjectInputStream(in);
			Object clone=oin.readObject();
			oout.close();
			out.close();
			oin.close();
			in.close();
			return clone;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static class CloneOutputStream extends OutputStream {

		ArrayList<Byte> bytes=new ArrayList<Byte>();
		
		@Override
		public void write(int b) throws IOException {
			bytes.add((byte) b);
		}
		
		public CloneInputStream toInputStream() {
			return new CloneInputStream(bytes);
		}

	}
	
	private static class CloneInputStream extends InputStream {

		ArrayList<Byte> bytes=new ArrayList<Byte>();
		int place=0;
		
		public CloneInputStream(ArrayList<Byte> bytes) {
			this.bytes=bytes;
		}

		@Override
		public int read() throws IOException {
			// TODO Auto-generated method stub
			int bt=bytes.get(place);
			place++;
			return bt;
		}

	}
}
