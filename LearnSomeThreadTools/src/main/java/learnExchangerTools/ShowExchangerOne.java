package learnExchangerTools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import threadUtil.ThreadUtils;

/**
 * In this case,we will constructor two threads, one make the buffer filled,one
 * make the buffer empty. When buffer is filled,we will make this thread to wait
 * until another thread is empty which exchange their buffer,then make them
 * invoke continue.
 * 
 * @author Yuanguohao
 *
 * @date Nov 5, 2017 8:37:07 PM
 *
 */
public class ShowExchangerOne {

	/**
	 * The size of buffer
	 */
	private static final Integer BUFFER_LENGTH = 20;
	/**
	 * The type of buffer,in this case,we use a list collection
	 */
	private static final Exchanger<List<Character>> EXCHANGER = new Exchanger<>();

	public static void main(String[] args) {
		Thread fillThread = new Thread(new FillingLoop());
		Thread emptyThread = new Thread(new emptyLoop());
		fillThread.start();
		emptyThread.start();
	}

	/**
	 * This thread will make thread filled then waitting empty thread exchange
	 * 
	 * @author Yuanguohao
	 *
	 * @date Nov 5, 2017 9:12:44 PM
	 *
	 */
	static class FillingLoop implements Runnable {
		private List<Character> buffer = new ArrayList<Character>();;

		@Override
		public void run() {
			while (buffer != null) {
				readBuffer(buffer);
				if (buffer.size() == BUFFER_LENGTH) {
					ThreadUtils.sleepSeconds(2);
					try {
						buffer = EXCHANGER.exchange(buffer);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * This thread will make buffer empty then will wait filled thread to exchange
	 * 
	 * @author Yuanguohao
	 *
	 * @date Nov 5, 2017 9:13:27 PM
	 *
	 */
	static class emptyLoop implements Runnable {
		private List<Character> buffer = new ArrayList<Character>();
		int counter = 0;

		@Override
		public void run() {
			while (buffer != null) {
				if (buffer.size() != 0) {
					consumeBuffer(buffer);
				} else {
					try {
						buffer = EXCHANGER.exchange(buffer);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private void consumeBuffer(List<Character> buffer2) {
			System.out.println(buffer2.toString());
			buffer2.removeAll(buffer2);
		}
	}

	private static void readBuffer(List<Character> buffer2) {
		buffer2.add('y');
	}
}
