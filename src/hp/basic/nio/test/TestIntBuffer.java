package hp.basic.nio.test;

import java.nio.Buffer;
import java.nio.IntBuffer;

/** 
* @author  hpym365
*/
public class TestIntBuffer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		IntBuffer buff = IntBuffer.allocate(10);
		OutBuff(buff);
		
		for(int i=0;i<4;i++){
			buff.put(i*2+1);
		}
		OutBuff(buff);
		buff.flip();
		OutBuff(buff);
		while(buff.remaining()>0){
			int i = buff.get();
			System.out.println(i+" ");
			OutBuff(buff);
		}
	}
	
	public static void OutBuff(Buffer buff){
		int position = buff.position();
		int capacity = buff.capacity();
		int limit = buff.limit();
		int remaining = buff.remaining();
		System.out.println("buff位置position: "+position+" capacity:" +capacity+" limit:"+limit+" remaining:"+remaining);
	}

}
 