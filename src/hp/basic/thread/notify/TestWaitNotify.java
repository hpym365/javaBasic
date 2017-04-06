package hp.basic.thread.notify;

public class TestWaitNotify {
	public static void main(String[] args) throws Exception {
		Thread myThread = new MyThread();
		myThread.start();

		synchronized (myThread) {
			myThread.wait();
		}

		System.out.println("wait结束.");
	}
}

class MyThread extends Thread {
	public void run() {
		System.out.println(getName() + "开始sleep");
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(getName() + "结束sleep");
	}
}