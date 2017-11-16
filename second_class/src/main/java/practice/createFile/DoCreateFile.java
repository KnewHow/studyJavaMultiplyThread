package practice.createFile;

import java.util.ArrayList;
import java.util.List;

import practice.threadPool.DefaultThreadPool;
import practice.threadPool.Job;

public class DoCreateFile {
	
	public static void main(String[] args) {
		String dir = DoCreateFile.class.getResource("").getPath();
		
		List<String> filePathList = new ArrayList<String>();
		String filePath = dir +  "target.txt";
		DefaultThreadPool pool = new DefaultThreadPool(21);
		WriteWordToTargerFileJob writeJob = new WriteWordToTargerFileJob(filePath);
		pool.execute(writeJob);
		for (int i = 0; i < 10; i++) {
			filePathList.add(filePath);
			Job readJob = new CreateWordJob();
			pool.execute(readJob);
		}
	}
	
}
