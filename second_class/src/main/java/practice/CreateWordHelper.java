package practice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateWordHelper {

	private static final long FILE_SIZE = 1 * 1000 * 1000 * 1000;

	public static void main(String[] args) {
		String filePath = CreateWordHelper.class.getClassLoader().getResource("practice/targetFile.txt").getPath();
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
	 * create a Chinese word by random,
	 * the max length of word is eight
	 * @return
	 */
	public static String createRandomChinesWord() {
		int wordLengt = getNomoreRandomNumber(8);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<wordLengt;i++) {
			char ch =  (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00)));
			sb.append(String.valueOf(ch));
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @return a random word,but it is not actual
	 * word,it is composed by random alphabet
	 */
	public static String createRandomEnglishWord() {
		int workLength = getNomoreRandomNumber(10);
		StringBuilder sb = new StringBuilder();
		System.out.println(workLength);
		for(int i=0;i<workLength;i++) {
			char ch = (char)(Math.random()*26+65);
			sb.append(String.valueOf(ch));
		}
		if(getNomoreRandomNumber(1000)%2==0) {
			return sb.toString().toUpperCase();
		}else {
			return sb.toString().toLowerCase();
		}
		
	}
	
	/**
	 * add <code>\r\n</code> between word
	 * @param str
	 * @return
	 */
	public static String addLineBetweenWord(String str) {
		Random random = new Random();
		int probability = random.nextInt(100);
		StringBuilder sb = new StringBuilder();
		/**
		 * Let 10% string add line
		 */
		if(probability>90) {
			if(str.length()<1) {
				return str;
			}
			/**
			 * add line random
			 */
			int index = (int) (random.nextInt(str.length()));
			sb.append(str.substring(0,index));
			sb.append("\n");
			sb.append(str.substring(index, str.length()));
		}else {
			return str;
		}
		return sb.toString();
		
	}
	
	/**
	 * Get a random number,the number will between
	 * 0 and length
	 * @param length
	 * @return
	 */
	private static int getNomoreRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}

}
