package learnCountDownLatch.my;

import java.util.ArrayList;
import java.util.List;

import threadUtil.ThreadUtils;

/**
 * In this case,We will let main thread to wait
 * until other thread executed
 * @author Yuanguohao
 *
 * @date Nov 2, 2017 7:14:41 PM
 *
 */
public class ShowMainWaitOtherThreadByJoin {
	public static void main(String[] args) throws Exception {
		List<Thread> threads = new ArrayList<Thread>();
		//let all thread start first
		for(int i=0;i<10;i++) {
			Thread thread = new Thread(new DemoThread(),"thread-"+i);
			threads.add(thread);
			thread.start();
		}
		
		//let main thread wait and make other threads executed
		for(Thread t:threads) {
			t.join();
		}
		System.out.println(Thread.currentThread().getName()+"@ is exceting");
	}
	
	static class DemoThread implements Runnable{
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"@ is exceting");
			ThreadUtils.sleepSeconds(1);
		}
		
	}
}
