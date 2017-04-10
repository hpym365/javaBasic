package hp.basic.nio.test.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author hpym365
 */
public class UdpChannelClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		DatagramChannel channel = DatagramChannel.open();
		DatagramSocket socket = channel.socket();
		socket.bind(new InetSocketAddress(2234));

		while (true) {
			Thread.sleep(2000);
			byte[] arr = ("今天天气好晴朗"+System.currentTimeMillis()).getBytes();
			ByteBuffer bbf = ByteBuffer.allocate(arr.length);
			bbf.put(arr);
			bbf.flip();
			channel.send(bbf,new InetSocketAddress("127.0.0.1",1234));
		}
	}

}
