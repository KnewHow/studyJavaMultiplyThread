package practice.createFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import practice.threadPool.Job;

public class WriteWordToTargerFileJob implements Job {

	public static volatile boolean IS_RUN = true;

	private static LinkedList<String> wordList = new LinkedList<>();
	/**
	 * Each file size limited,we will run nine times
	 */
	private static final Long FILE_SIZE = 2L * 1000 * 1000*1000 ;
	

	/**
	 * The file path of target file
	 */
	private String filePath;

	public WriteWordToTargerFileJob(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Override
	public void run() {
		File file = new File(filePath);
		FileWriter writer = null;
		try {
			file.createNewFile();
			writer = new FileWriter(file);
			while (file.length() < FILE_SIZE) {
				if (wordList != null && wordList.size() != 0) {
					writer.write(wordList.removeFirst());
					writer.flush();
				}
			}
			IS_RUN = false;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void putWords(String words) {
		synchronized (wordList) {
			wordList.addLast(words);
		}
	}

}
