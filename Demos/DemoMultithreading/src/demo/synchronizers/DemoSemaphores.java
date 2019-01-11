package demo.synchronizers;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DemoSemaphores {

	public static void doDemo() {

		Semaphore semaphore = new Semaphore(2, true);

		MyRunnableTask[] tasks = new MyRunnableTask[5];

		for (int i = 0; i < 5; i++) {
			tasks[i] = new MyRunnableTask("MyRunnableTask" + i, semaphore);
			try {
				new Thread(tasks[i]).start();
			}
			catch(Exception ex) {}
		}
	}
}


class MyRunnableTask implements Runnable {

	private static final Random rand = new Random();

	private String taskName;
	private Semaphore semaphore;

	public MyRunnableTask(String name, Semaphore s) {
		taskName = name;
		semaphore = s;
	}

	public void run() {

		for (int i = 0; i < 2; ++i) {
	
			// Perform a non-critical op (no need for semaphore).
			nonCriticalOp();

			// Perform a critical op (need to acquire/release semaphore).
			try { 
				System.out.printf("    %s waiting to acquire semaphore (available permits is %d).\n", taskName, semaphore.availablePermits());
				semaphore.acquire(); 
				System.out.printf("****%s acquired semaphore (available permits is %d).\n", taskName, semaphore.availablePermits());
				criticalOp();
				semaphore.release();
			}
			catch (InterruptedException ex) {}
		}
	}


	private void nonCriticalOp() {
		System.out.printf("        %s entering nonCriticalOp().\n", taskName);
		beBusy();
		System.out.printf("        %s leaving nonCriticalOp().\n", taskName);
	}

	
	private void criticalOp() {
		System.out.printf("    %s entering criticalOp().\n", taskName);
		beBusy();
		System.out.printf("    %s leaving criticalOp().\n", taskName);
	}

	
	private void beBusy() {
		try {
			Thread.sleep(rand.nextInt(500));
		} catch (InterruptedException e) {
		}
	}
}