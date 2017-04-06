package hp.basic.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class TestClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Selector sel = Selector.open();
		SocketChannel sc = SocketChannel.open(new InetSocketAddress(1234));

		sc.configureBlocking(false);
		sc.register(sel, SelectionKey.OP_READ);

		String msg = "测试msg";
		ByteBuffer bbf = ByteBuffer.wrap(msg.getBytes());
		sc.write(bbf);
		
		
		while(true){
			sel.select();
			
			Set<SelectionKey> keys = sel.selectedKeys();
			for(SelectionKey key : keys){
				keys.remove(key);
				if(key.isReadable()){
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer bbf1 = ByteBuffer.allocate(2048);
					int read = channel.read(bbf1);
					bbf1.flip();
					byte[] array=new byte[read];
					bbf1.get(array);
					String res = new String(array,"utf-8");
					System.out.println("客户端接收到的信息为:"+res);
				}
			}
		}

	}

}
