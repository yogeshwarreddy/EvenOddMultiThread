package main;

public class EvenOdd extends Thread {

	static int counter = 1;

	public EvenOdd(boolean isEven) {
		super(isEven ? "Even" : "Odd");
	}

	@Override
	public void run() {
		super.run();
		printOddOrEven();
	}

	public static synchronized void printOddOrEven() {
		while (counter <= 100) {
			try {
				if ((counter % 2 == 0 && currentThread().getName().equals("Even"))
						|| (counter % 2 != 0 && currentThread().getName().equals("Odd"))) {
					System.out.println(currentThread().getName() + " " + counter++);
				} else {
					EvenOdd.class.wait();
				}
				EvenOdd.class.notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		EvenOdd even = new EvenOdd(true);
		EvenOdd odd = new EvenOdd(false);
		even.start();
		odd.start();
	}

}
