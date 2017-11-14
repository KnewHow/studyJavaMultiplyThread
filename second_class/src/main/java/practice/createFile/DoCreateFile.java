package practice.createFile;

import practice.threadPool.DefaultThreadPool;
import practice.threadPool.Job;

public class DoCreateFile {
	public static void main(String[] args) {
		String dir = DoCreateFile.class.getResource("").getPath();
		DefaultThreadPool pool = new DefaultThreadPool(9);
		for (int i = 0; i < 9; i++) {
			String filePath = dir +  + i + ".temp";
			System.out.println(filePath);
			Job job = new CreateTargetFileJob(filePath);
			pool.execute(job);
		}
	}
}
