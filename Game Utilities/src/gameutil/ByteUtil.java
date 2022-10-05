package gameutil;

import gameutil.text.Console;

public class ByteUtil {
	
	static final int[] GET_BYTE_OPS=new int[] {
		0x000000ff,
		0x0000ff00,
		0x00ff0000,
		0xff000000,
	};
	
	static final int[] CLEAR_BYTE_OPS=new int[] {
			0xffffff00,
			0xffff00ff,
			0xff00ffff,
			0x00ffffff,
		};
	
	public static byte getByte(int i,byte address) {
		return (byte)(((i&GET_BYTE_OPS[address])>>>(address*8)));
	}
	
	public static int getUnsignedByte(int i,byte address) {
		return (((i&GET_BYTE_OPS[address])>>>(address*8)));
	}
	
	public static int getUnsigned(byte b) {
		return b&0xff;
	}
	
	public static int setByte(int i,byte value,byte address) {
		int val=getUnsigned(value);
		return (i&CLEAR_BYTE_OPS[address])|(val<<(8*address));
	}
	
	public static String toHex(byte b) {
		return String.format("%08X", b);
	}
	
	public static String toHex(int i) {
		return String.format("%08X", i);
	}
}
