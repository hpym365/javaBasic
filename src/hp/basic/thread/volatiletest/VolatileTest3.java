package hp.basic.thread.volatiletest;

public class VolatileTest3 {

	static  boolean state;//volatile

	public static void main(String[] args) throws InterruptedException {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					state = true;
					while (state) {
//						System.out.println("dosomething"+state);
						new VolatileTest3().sleep();;
						int i=0;
						boolean s = state;
//						System.out.println(i);
					}
				}
			});
			t1.start();
			Thread.sleep(11);
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					state = false;
					System.out.println("change state false");
				}

			});
			t2.start();
	}
	
	public void sleep(){
		int i=0;
		i=5;
//		System.out.println(5);
	}

}
