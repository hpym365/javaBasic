package hp.basic.thread;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Cas {

	public static AtomicLong count = new AtomicLong(0);
	public static int count1 = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newFixedThreadPool(5);
		CompletionService comp = new ExecutorCompletionService<>(exec);
		exec.execute(new Add());
		exec.execute(new Add());
		exec.execute(new Add());
		exec.execute(new Add());
		exec.execute(new Add());
		exec.shutdown();

	}

	static class Add implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 10000; i++) {
				add1();
				add();
			}
			System.out.println(count.get());
			System.out.println(count1);
		}

	}

	public static void add() {
		boolean upd = false;
		while (!upd) {
			Long i = count.get();
			upd = count.compareAndSet(i, i + 1);
		}
	}
	
	public static void add1(){
		count1++;
	}

}
