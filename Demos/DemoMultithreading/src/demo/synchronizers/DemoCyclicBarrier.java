package demo.synchronizers;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DemoCyclicBarrier {

	public static void doDemo() {
		
		// We're going to perform concurrent processing on the strings in this array.
		String[] messages = new String[] { "Hello world", "Bonjour", "Hola", "Prynhawn da" };
		
		System.out.printf("[%s] Creating a CyclicBarrier that requires %d threads to reach a common point.\n", new Date(), messages.length);
		CyclicBarrier barrier = new CyclicBarrier(messages.length,
				                                  new Runnable() {
													public void run() { System.out.println("ALL THREADS REACHED BARRIER!!!"); }
												  });

		// Use the CyclicBarrier for the 1st time...
		// Create a separate thread to process each row, to convert to UPPERCASE. 
		// The threads must wait for each other to finish, via the CyclicBarrier.
		for (int i = 0; i < messages.length; i++) {
		    Runnable r = new MyStringProcessorWithCyclicBarrier(messages, i, true, barrier);
			new Thread(r).start();
		}
		
		// Wait for all threads to have reached the barrier.
		try {
			barrier.await();
		} 
		catch (InterruptedException e) {} 
		catch (BrokenBarrierException e) {}


		// Use the CyclicBarrier for the 2nd time...
		// Create a separate thread to process each row, to convert to lowercase. 
		// The threads must wait for each other to finish, via the CyclicBarrier.
		for (int i = 0; i < messages.length; i++) {
		    Runnable r = new MyStringProcessorWithCyclicBarrier(messages, i, false, barrier);
			new Thread(r).start();
		}
		
		// Wait for all threads to have reached the barrier.
		try {
			barrier.await();
		} 
		catch (InterruptedException e) {} 
		catch (BrokenBarrierException e) {}

		
		System.out.printf("[%s] ================THE END================\n", new Date());
	}
}

class MyStringProcessorWithCyclicBarrier implements Runnable {

	private String[] messages;
	private int row;
	private boolean toUpperCase;
	private CyclicBarrier barrier;
	
	public MyStringProcessorWithCyclicBarrier(String [] messages, int r, boolean toUpperCase, CyclicBarrier barrier) {
		this.messages = messages;
		this.row = r;
		this.toUpperCase = toUpperCase;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			System.out.printf("[%s] %s started processing.\n", new Date(), messages[row]);
			if (toUpperCase)
				messages[row] = messages[row].toUpperCase();
			else
				messages[row] = messages[row].toLowerCase();
			Thread.sleep(messages[row].length() * 1000);

			// Tell the CyclicBarrier we're reached the barrier point.
			// If we're the last to arrive, then output the work of all the threads.
			if (barrier.await() == 0) {
				for (int i = 0; i < messages.length; i++) {
					System.out.printf("[%s] %s result.\n", new Date(), messages[i]);
				}
			}
		} 
		catch (InterruptedException ex) {} 
		catch (BrokenBarrierException e) {}
	}
}
