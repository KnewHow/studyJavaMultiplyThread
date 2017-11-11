package practice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateTargetFile {

	private static final long FILE_SIZE = 1 * 1000 * 1000 * 1000;

	public static void main(String[] args) {
		String filePath = CreateTargetFile.class.getClassLoader().getResource("practice/targetFile.txt").getPath();
		writeFile(filePath);
		System.out.println(filePath);
	}

	public static void writeFile(String filePath) {
		File file = new File(filePath);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			while (file.length() < FILE_SIZE) {
				writer.write("java");
				writer.flush();
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

	/**
	 * create a Chinese character by random
	 * @return
	 */
	public static String createChinesRandom() {
		char ch =  (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00)));
		return String.valueOf(ch);
	}
	
	/**
	 * 
	 * @return a random word,but it is not actual
	 * word,it is composed by random alphabet
	 */
	public static String createRandomWord() {
		Random random = new Random(10);
		for(int i=0;i<100;i++) {
			int number = random.nextInt();
			System.out.println(number);
		}
		return null;
	}

}
