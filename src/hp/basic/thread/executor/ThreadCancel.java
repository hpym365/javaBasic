package hp.basic.thread.executor;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadCancel {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newFixedThreadPool(10);
		
		ExecutorCompletionService<Integer> comp = new ExecutorCompletionService<Integer>(exec);

		for (int i = 0; i < 100; i++) {
			exec.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
//					int[] arr = new int[9999999];
//					for(int j=0;j<9999999;j++){
//						arr[j]=j;
//					}
					return 1;
				}
			});
		}
		List<Runnable> shutdownNow = exec.shutdownNow();
		int result = 0;
		while (true) {
			Future<Integer> take = null;
			take = comp.take();
			try {
				if (take == null) {
					break;
				}
				Integer i = take.get();
				result = result + i;
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Thread.currentThread().interrupt();
		System.out.println(shutdownNow);
		System.out.println(result);
	}

}
