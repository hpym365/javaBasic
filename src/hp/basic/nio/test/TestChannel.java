package hp.basic.nio.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/** 
* @author  hpym365
*/
public class TestChannel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		writeToFile();
		readFromFile();
	}
	
	public static void readFromFile() throws Exception{
		FileInputStream fin = new FileInputStream("D:\\\\w.txt");
		FileChannel channel = fin.getChannel();
		
		ByteBuffer bbf = ByteBuffer.allocate((int) channel.size());
		
		int read = channel.read(bbf);
		bbf.flip();
		byte[] arr = bbf.array();
		System.out.println(arr.toString());
		String string = new String(arr,"gbk");
		System.out.println(string);
		while(bbf.hasRemaining()){
			byte b = bbf.get();
			System.out.println((char)b);
		}
	}
	
	public static void writeToFile() throws Exception{
		FileOutputStream fos = new FileOutputStream("d:\\\\w.txt");
		FileChannel channel = fos.getChannel();
		String str = "NIO测试234";
		byte[] bytes = str.getBytes();
		ByteBuffer bbf = ByteBuffer.wrap(bytes);
//		ByteBuffer bbf = ByteBuffer.allocate(bytes.length);
//		bbf.put(bytes);
//		bbf.flip();
		channel.write(bbf);
		fos.close();
	
	
	}

}
 