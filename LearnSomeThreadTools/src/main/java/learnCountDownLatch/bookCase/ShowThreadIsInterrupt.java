package learnCountDownLatch.bookCase;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ShowThreadIsInterrupt {
	private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(2);
	public static void main(String[] args) {
		Thread thread = new Thread(new DemoThread(CYCLIC_BARRIER));
		thread.start();
		thread.interrupt();
		try {
			CYCLIC_BARRIER.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(CYCLIC_BARRIER.isBroken());
	}
	
	static class DemoThread implements Runnable{
		private CyclicBarrier cyclicBarrier;

		public DemoThread(CyclicBarrier cyclicBarrier) {
			super();
			this.cyclicBarrier = cyclicBarrier;
		}
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+" @ excuted");
			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		
	}
}
