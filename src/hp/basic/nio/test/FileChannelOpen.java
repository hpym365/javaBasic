package hp.basic.nio.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.file.FileSystems;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author hpym365
 * @version 创建时间：2017年4月4日 下午9:35:57 类说明
 */
public class FileChannelOpen {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Selector selector = Selector.open();
		Path path =FileSystems.getDefault().getPath("d:","w.txt");
		FileChannel fc = FileChannel.open(path,  StandardOpenOption.READ);
		ByteBuffer bbf = ByteBuffer.allocate(20);
		fc.read(bbf);
		bbf.flip();
		byte[] array = bbf.array();
		String str = new String(array,"GBK");
		System.out.println(str);
	}

}
