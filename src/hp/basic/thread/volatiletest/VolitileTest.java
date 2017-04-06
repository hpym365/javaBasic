package hp.basic.thread.volatiletest;

public class VolitileTest {

	private static volatile int MY_INT = 0;

	public static void main(String[] args) {
		new ChangeListener().start();
		new ChangeMaker().start();
	}

	static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while ( local_value < 5){
                if( local_value!= MY_INT){
                    System.out.println(String.format("Got Change for MY_INT : %S", MY_INT));
                    local_value= MY_INT;
                }
            }
            System.out.println(System.nanoTime());
        }
    }

    static class ChangeMaker extends Thread{
        @Override
        public void run() {

            int local_value = MY_INT;
            while (MY_INT <5){
                System.out.println(String.format("Incrementing MY_INT to %S", MY_INT+1+"  ")+System.nanoTime());
//                MY_INT = ++local_value;
                ++MY_INT;
               
            }
        }
    }
}