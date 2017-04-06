package hp.basic.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/** 
* @author  hpym365
*/
public class TestSelectorServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Selector sel = Selector.open();
		
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);//非阻塞
		
		ServerSocket socket = ssc.socket();
		SocketAddress endpoint = new InetSocketAddress(1234);
		socket.bind(endpoint);
		
		ssc.register(sel, SelectionKey.OP_ACCEPT);
		
		
		while(true){
			int select = sel.select();
			Set<SelectionKey> keys = sel.selectedKeys();
			
			for(SelectionKey s:keys){
				sel.selectedKeys().remove(s);
				if(s.isAcceptable()){
					ServerSocketChannel server = (ServerSocketChannel) s.channel();  
			        SocketChannel channel = server.accept();  
			        channel.configureBlocking(false);  
			        channel.register(sel, SelectionKey.OP_READ);  
				}
				
				if(s.isConnectable())
					System.out.println("事件:.isConnectable()");
				
				if(s.isReadable())
					System.out.println("事件:isReadable()");
				
				if(s.isWritable())
					System.out.println("事件:isWritable()");
				
				System.out.println(s);
			}
		}
	}

}
 