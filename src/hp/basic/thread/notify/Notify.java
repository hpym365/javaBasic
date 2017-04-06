package hp.basic.thread.notify;

public class Notify {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Notify n = new Notify();
		Test tt1 = new Test(n);
		Test1 tt2 = new Test1(n);
		Test1 tt3 = new Test1(new Notify());
		Thread t1 = new Thread(tt1, "thread 1");
		Thread t2 = new Thread(tt2, "thread 2");
		Thread t3 = new Thread(tt3, "thread 3");
		t2.start();
		Thread.sleep(1000);
		t3.start();
		Thread.sleep(1000);
		t1.start();
	}
	
	public void doNotify() {
		synchronized (this) {
			this.notifyAll();
		}
	}

	
	public void dowait() {
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static class Test implements Runnable {
		private Notify n;

		public Test(Notify n) {
			this.n = n;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				int i = (int) (Math.random() * 100);
				if (i == 99) {
					n.doNotify();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		
	}

	static class Test1 implements Runnable {
		private Notify n;

		public Test1(Notify n) {
			this.n = n;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				System.out.println("sleep 线程test1 等待唤醒哈" + ":我是线程" + Thread.currentThread().getName());
				n.dowait();

				System.out.println("被唤醒了哈哈哈哈 我又困了。。。继续睡觉" + ":我是线程" + Thread.currentThread().getName());
				n.dowait();
			}
		}

		
	}

}
