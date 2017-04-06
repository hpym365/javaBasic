package hp.basic.thread.falsesharing;

public class FalseSharing implements Runnable {

	public final static int NUM_THREADS = 4; // change
	public final static long ITERATIONS = 500L * 1000L;
	private final int arrayIndex;

	private static VolatileLong[] longs1 = new VolatileLong[NUM_THREADS];
	private static VolatileLong2[] longs2 = new VolatileLong2[NUM_THREADS];
	private static VolatileLong3[] longs3 = new VolatileLong3[NUM_THREADS];
	static {
		for (int i = 0; i < longs1.length; i++) {
			longs1[i] = new VolatileLong();
		}
		for (int i = 0; i < longs2.length; i++) {
			longs2[i] = new VolatileLong2();
		}
		for (int i = 0; i < longs3.length; i++) {
			longs3[i] = new VolatileLong3();
		}
	}
//duration1 = 54288358
//duration2 = 37392575
//duration3 = 26648935
	public FalseSharing(final int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}

	public static void main(final String[] args) throws Exception {
		long start = System.nanoTime();
		runTest();
		System.out.println("duration = " + (System.nanoTime() - start));
	}

	private static void runTest() throws InterruptedException {
		Thread[] threads = new Thread[NUM_THREADS];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new FalseSharing(i));
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
	}

	public void run() {
		long i = ITERATIONS + 1;
		while (0 != --i) {
			longs3[arrayIndex].value = i;
		}
	}

	public final static class VolatileLong {
		public volatile long value = 0L;
	}

	// long padding避免false sharing
	// 按理说jdk7以后long padding应该被优化掉了，但是从测试结果看padding仍然起作用
	public final static class VolatileLong2 {
		volatile long p0, p1, p2, p3, p4, p5, p6;
		public volatile long value = 0L;
		volatile long q0, q1, q2, q3, q4, q5, q6;
	}

	// jdk8新特性，Contended注解避免false sharing
	// Restricted on user classpath
	// Unlock: -XX:-RestrictContended
	@sun.misc.Contended
	public final static class VolatileLong3 {
		public volatile long value = 0L;
	}
}