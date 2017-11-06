package makeDatabaseConnectionModel;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Yuanguohao
 *
 * @date Nov 6, 2017 7:38:06 PM
 *
 */
public class ConnectionPoolTest {
	/**
	 * The size of connection pool
	 */
	private static final Integer CONNECTION_POOL_SIZE = 10;
	/**
	 * The total times witch all threads get connection from pool
	 */
	private static Integer TOTAL_GET_AMOUT = 50;

	/**
	 * The amounts of thread
	 */
	private static final Integer THREAD_AMOUNT = 20;

	/**
	 * The time the thread witch to wait
	 */
	private static final Long WAIT_MILL_SECONDS = 1000L;

	private static ConnectionPool pool = new ConnectionPool(CONNECTION_POOL_SIZE);

	private static CountDownLatch start = new CountDownLatch(1);

	private static CountDownLatch end;

	public static void main(String[] args) throws InterruptedException {
		end = new CountDownLatch(THREAD_AMOUNT);
		AtomicInteger got = new AtomicInteger();
		AtomicInteger notgot = new AtomicInteger();
		for(int i=0;i<THREAD_AMOUNT;i++) {
			Thread thread = new Thread(new RunnerThread(TOTAL_GET_AMOUT, got, notgot),"connectionThread"+i);
			thread.start();
		}
		start.countDown();
		end.await();
		System.out.println("total invoke: "+(THREAD_AMOUNT*TOTAL_GET_AMOUT));
		System.out.println("get times: "+got);
		System.out.println("no get times: "+notgot);
		float nogotNumber = notgot.floatValue();
		System.out.println("the tie of no get is "+((nogotNumber)/(THREAD_AMOUNT*TOTAL_GET_AMOUT)*100)+"%");
	}

	static class RunnerThread implements Runnable {

		/**
		 * The total counter
		 */
		private int count;
		private AtomicInteger got;

		private AtomicInteger notgot;

		public RunnerThread(int count, AtomicInteger got, AtomicInteger notgot) {
			super();
			this.count = count;
			this.got = got;
			this.notgot = notgot;
		}

		@Override
		public void run() {
			try {
				start.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (count > 0) {
				try {
					Connection connection = pool.fetchConnection(WAIT_MILL_SECONDS);
					if (connection != null) {
						try {
							connection.createStatement();
							connection.commit();
						} finally {
							pool.releaseConnection(connection);
							got.incrementAndGet();
						}
					} else {
						notgot.incrementAndGet();
					}
				} catch (Exception e) {
				} finally {
					count--;
				}
			}
			/**
			 * tell main thread this thread executed finish
			 */
			end.countDown();
		}

	}
}
