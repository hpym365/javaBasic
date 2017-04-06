package hp.basic.thread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletionServiceTest {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				ExecutorService exec = Executors.newCachedThreadPool();

				CompletionService<Integer> compexec = new ExecutorCompletionService<Integer>(exec);

				for (int i = 0; i < 10; i++) {
					compexec.submit(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							return 10;
						}

					});
				}
				int total = 0;
				while (true) {
					Future<Integer> poll = null;
					try {
						poll = compexec.poll(10, TimeUnit.MICROSECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (poll != null) {
						try {
							total = total + poll.get();
						} catch (InterruptedException | ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						break;
					}
				}
				System.out.println(total);
				exec.shutdownNow();
			}

		});
		thread.start();
		thread.interrupt();
	}
}
