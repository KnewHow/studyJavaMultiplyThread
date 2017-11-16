package practice.createFile;

import practice.threadPool.Job;

/**
 * Create target file with 9GB which has banned word and other word.
 * 
 * We prepare nine jobs then let nine thread to executing them, we let each jobs
 * to create a file with 1GB.
 * 
 * Maybe it will produce out of memory,but it is my first try,I will improve my
 * algorithms if the way fail.
 * 
 * 
 * @author Yuanguohao
 *
 * @date Nov 14, 2017 8:09:01 PM
 *
 */
public class CreateWordJob implements Job {

	
	/**
	 * The JVM will flush stream after  the loop run followd times
	 */
	private static final long FLUSH_COUNTER=1000*1000;
	
	public void writeFile() {
		int flushCounter=0;
		StringBuilder sb = new StringBuilder();
		CreateWordHelper helper = new CreateWordHelper();
		int counter = 0;
			while (WriteWordToTargerFileJob.IS_RUN) {
				sb.append(helper.createTargetFileWord());
				counter++;
				// flush file
				if (counter == FLUSH_COUNTER) {
					//good for GC
					WriteWordToTargerFileJob.putWords(sb.toString());
					sb = null;
					sb = new StringBuilder();
					System.out.println(Thread.currentThread().getName()+" @ flush "+flushCounter+" times .");
					flushCounter++;
					counter = 0;
				}
			}
			
	}

	@Override
	public void run() {
		writeFile();
	}
}
