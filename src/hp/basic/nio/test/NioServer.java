package hp.basic.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

public class NioServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Selector sel = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(2222));
		ssc.configureBlocking(false);
		ssc.register(sel, SelectionKey.OP_ACCEPT);
		
		while(true){
			sel.select();
			
			Set<SelectionKey> selectedKeys = sel.selectedKeys();
			
			for(SelectionKey key : selectedKeys){
//				sel.selectedKeys().remove(key);
				if(key.isAcceptable()){
					System.out.println("注册通过");
					System.out.println("isAcceptable");
					// 调用accept方法接受连接，产生服务器端对应的SocketChannel
//					SocketChannel sc = ssc.accept();

					ssc.accept();
					SelectableChannel sc = key.channel();
					
					
//					// 设置采用非阻塞模式
//					sc.configureBlocking(false);
//					// 将该SocketChannel也注册到selector
//					sc.register(sel, SelectionKey.OP_READ);
//					key.interestOps(SelectionKey.OP_ACCEPT);
				}
			}
		}
	}

}
