package demo.synchronizers;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class DemoCountDownLatch {

	public static void doDemo() {
		
		// We're going to perform concurrent processing on the strings in this array.
		String[] messages = new String[] { "Hello world", "Bonjour", "Hola", "Prynhawn da" };
		
		System.out.printf("[%s] Creating a CountDownLatch that requires %d threads to reach a common point.\n", new Date(), messages.length);
		CountDownLatch latch = new CountDownLatch(messages.length);

		// Create a separate thread to process each row. 
		// The threads must wait for each other to finish, via the CountDownLatch.
		for (int i = 0; i < messages.length; i++) {
		    Runnable r = new MyStringProcessorWithLatch(messages, i, latch);
			new Thread(r).start();
		}
		
		// Wait for all threads to finish their work.
		try {
			latch.await();
		} 
		catch (InterruptedException e) {} 

		for (int i = 0; i < messages.length; i++) {
			System.out.printf("[%s] %s result.\n", new Date(), messages[i]);
		}
		
		System.out.printf("[%s] ================THE END================\n", new Date());
	}
}


class MyStringProcessorWithLatch implements Runnable {

	private String[] messages;
	private int row;
	private CountDownLatch latch;
	
	public MyStringProcessorWithLatch(String [] messages, int r, CountDownLatch latch) {
		this.messages = messages;
		this.row = r;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			System.out.printf("[%s] %s started processing.\n", new Date(), messages[row]);
			messages[row] = messages[row].toUpperCase(); 
			Thread.sleep(messages[row].length() * 1000);

			// Count-down the latch, to indicate we've finished our bit of work.
			latch.countDown();
			
			// Do you want to wait for all the other threads to finish? If so, uncomment the next line.
			// latch.await();
			
			// Do you want to do some more processing now? If so, uncomment the next line.
			// System.out.printf("[%s] %s finished processing.\n", new Date(), messages[row]);
		} 
		catch (InterruptedException ex) {} 
	}
}
