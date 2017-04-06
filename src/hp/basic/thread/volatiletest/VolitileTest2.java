package hp.basic.thread.volatiletest;

public class VolitileTest2 {

	private static int MY_INT = 0;

	public static void main(String[] args) {
		new ChangeListener().start();
		new ChangeMaker().start();
	}

	public synchronized static void add() {
		MY_INT = MY_INT + 1;
	}

	static class ChangeListener extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 250000; i++) {
				add();
			}
			System.out.println("线程1执行完毕：" + MY_INT);
		}
	}

	static class ChangeMaker extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 250000; i++) {
				add();
			}
			System.out.println("线程2执行完毕：" + MY_INT);
		}
	}
}