package hp.basic.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NioClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Selector sel = Selector.open();
		while (true) {
			try {
				SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 1234));// 连接并打开套接字

				Thread.sleep(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
