package hp.basic.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class TestServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Selector sel = Selector.open();
		//
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(1234));
		ssc.configureBlocking(false);
		ssc.register(sel, SelectionKey.OP_ACCEPT);

		while (sel.select() > 0) {
			Set<SelectionKey> keys = sel.selectedKeys();

			for (SelectionKey s : keys) {
				keys.remove(s);// 处理过的删除掉
				if (s.isAcceptable()) {
					ServerSocketChannel channel = (ServerSocketChannel) s.channel();
					SocketChannel sc = channel.accept();// 同意
					SocketAddress localAddress = channel.getLocalAddress();
					sc.configureBlocking(false);
					sc.register(sel, SelectionKey.OP_READ);
					if (channel == ssc) {
						System.out.println("channel==ssc  true");
						System.out.println(channel);
						System.out.println(s.readyOps());
					}
					System.out.println("请求连接" + localAddress.toString());
				}

				if (s.isReadable()) {
					SocketChannel channel = (SocketChannel) s.channel();
					ByteBuffer bbf = ByteBuffer.allocate(2048);
					try {
						System.out.println("isvalid:"+s.isValid());
						int read = channel.read(bbf);
						System.out.println("read:"+read);
						if (read > 0) {
							bbf.flip();
							byte[] array = new byte[read];
							bbf.get(array);
							String res = new String(array, "utf-8");
							System.out.println("服务端接收到的信息为:" + res);

							String returnMsg = "我看到了谢谢你!";
							bbf = ByteBuffer.wrap(returnMsg.getBytes());
							channel.write(bbf);
						} else if (read < 0) {
							System.out.println("对方关闭了");
							s.cancel();
							s.channel().close();
						}
//						s.interestOps(SelectionKey.OP_READ);
					} catch (Exception e) {
						s.cancel();
						s.channel().close();
						e.printStackTrace();
					}
				}
			}
		}
	}

}
