package hp.basic.thread.falsesharing;


public class FalseSharing1 implements Runnable {
	@sun.misc.Contended
	public final static class SS {
		public volatile int aa=0;
	}
	
	private static SS[] sarr = new SS[4];
	
	static {
		for (int i = 0; i < sarr.length; i++) {
			sarr[i] = new SS();
		}
	}
	//duration = 552126511
	//duration = 437281027
	
	//duration = 807199213  contended duration = 708095430
	//

	//duration = 109331280009
	//duration = 71853627655
	public static void main(String [] args){
		long start = System.nanoTime();
		Thread[] threads = new Thread[4];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new FalseSharing1());
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("duration = " + (System.nanoTime() - start));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 500*1000*1000 + 1;
		while (0 != --i) {
			sarr[2].aa = i;
		}
	}
	
}
