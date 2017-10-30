package showMainMethod;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 
 * @author Yuanguohao
 *
 * @date Oct 30, 2017 7:10:25 PM
 *
 */
public class ShowMainMultiply {
	/**
	 * Main method start with multiply thread
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.
				getThreadMXBean();
		ThreadInfo[] infos = threadMXBean.dumpAllThreads(false, false);
		for(ThreadInfo info:infos) {
			System.out.println("["+info.getThreadId()+"]"+info.getThreadName());
		}
	}
}
