package hp.basic.nio.test.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author hpym365
 */
public class UdpChannelServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		DatagramChannel channel = DatagramChannel.open();
		DatagramSocket socket = channel.socket();
		socket.bind(new InetSocketAddress(1234));

		while (true) {
			Thread.sleep(2000);
			ByteBuffer bbf = ByteBuffer.allocate(3000);
			SocketAddress receive = channel.receive(bbf);
			byte[] arr = bbf.array();
			System.out.println(new String(arr,"utf-8"));
			System.out.println("读取完毕");
		}
	}

}
