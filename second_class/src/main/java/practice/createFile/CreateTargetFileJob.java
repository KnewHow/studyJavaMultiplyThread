package practice.createFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
public class CreateTargetFileJob implements Job {

	/**
	 * Each file size limited
	 */
	private static final long FILE_SIZE = 1 * 1000 * 1000 * 2;

	/**
	 * The file path of target file
	 */
	private String filePath;

	public CreateTargetFileJob(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void writeFile() {
		int counter = 0;
		File file = new File(filePath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter writer = null;
		try {
			CreateWordHelper helper = new CreateWordHelper();
			writer = new FileWriter(file);
			while (file.length() < FILE_SIZE) {
				writer.write(helper.createTargetFileWord());
				counter++;
				// flush 1000 times
				if (counter == 10000) {
					counter = 0;
					writer.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		writeFile();
	}
}
