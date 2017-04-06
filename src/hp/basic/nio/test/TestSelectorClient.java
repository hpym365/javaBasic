package hp.basic.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author hpym365
 */
public class TestSelectorClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Selector sel = Selector.open();
		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		// Socket socket = sc.socket();
		//
		// socket.connect(new InetSocketAddress("localhost",1234));
		sc.connect(new InetSocketAddress(1234));

		sc.register(sel, SelectionKey.OP_CONNECT);

		sel.select();
		Set<SelectionKey> keys = sel.selectedKeys();
		for (SelectionKey s : keys) {
			if (s.isConnectable()) {
				System.out.println("事件isConnectable");
			}
			System.out.println(s);
		}

	}
	
}
