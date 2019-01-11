package demo.synchronizers;

import java.util.concurrent.Exchanger;

public class DemoExchanger {

	public static void doDemo() {

		Exchanger<String> exchanger = new Exchanger<String>();

		ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
		ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");

		new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();

	}
}

class ExchangerRunnable implements Runnable {

	private Exchanger<String> exchanger;
	private String data;

	public ExchangerRunnable(Exchanger<String> exchanger, String data) {
		this.exchanger = exchanger;
		this.data = data;
	}

	public void run() {
		try {
			String previous = this.data;
			this.data = this.exchanger.exchange(this.data);
			System.out.printf("%s exchanged %s for %s\n", Thread.currentThread().getName(), previous, this.data);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
