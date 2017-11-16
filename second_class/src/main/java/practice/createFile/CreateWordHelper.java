package practice.createFile;

import java.util.List;
import java.util.Random;

/**
 * Some method for create English word and Chines word random
 * 
 * @author Yuanguohao
 *
 * @date Nov 14, 2017 8:08:01 PM
 *
 */
public class CreateWordHelper {
	
	
	/**
	 * The banned word list
	 */
	private static volatile List<String> bannedWordList=null;


	/**
	 * create a Chinese word by random, the max length of word is eight
	 * 
	 * @return
	 */
	public static String createRandomChinesWord() {
		int wordLengt = getNomoreRandomNumber(8);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < wordLengt; i++) {
			char ch = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00)));
			sb.append(String.valueOf(ch));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @return a random word,but it is not actual word,it is composed by random
	 *         alphabet
	 */
	public static String createRandomEnglishWord() {
		int workLength = getNomoreRandomNumber(10);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < workLength; i++) {
			char ch = (char) (Math.random() * 26 + 65);
			sb.append(String.valueOf(ch));
		}
		if (getNomoreRandomNumber(1000) % 2 == 0) {
			return sb.toString().toUpperCase();
		} else {
			return sb.toString().toLowerCase();
		}

	}

	/**
	 * add <code>\r\n</code> between word
	 * 
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
		if (probability > 90) {
			if (str.length() < 1) {
				return str;
			}
			/**
			 * add line random
			 */
			int index = (int) (random.nextInt(str.length()));
			sb.append(str.substring(0, index));
			sb.append("\n");
			sb.append(str.substring(index, str.length()));
		} else {
			return str;
		}
		return sb.toString();

	}

	/**
	 * Get a random number,the number will between 0 and length
	 * 
	 * @param length
	 * @return
	 */
	private static int getNomoreRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}

	/**
	 * Create each word to create target file with 9GB One third part is distributed
	 * Chinese word, other third part is distributed English word, the last third
	 * part is distributed banned word. They will be write file random.Each of them
	 * will tithe to be add <code>\n</code> at random position to enforce
	 * distributed
	 * 
	 * @return
	 */
	public String createTargetFileWord() {
		getBannedWordList();
		Random random = new Random();
		int number = random.nextInt(10000);
		if (number < 3000) {
			return addLineBetweenWord(createRandomEnglishWord());
		} else if (number > 3000 && number < 6000) {
			return addLineBetweenWord(createRandomChinesWord());
		} else {
			return addLineBetweenWord(bannedWordList.get(random.nextInt(100)));
		}
	}
	
	/**
	 * Initialize banned word list,in all
	 */
	public void getBannedWordList(){
		if(bannedWordList==null) {
			synchronized (CreateWordHelper.class) {
				if(bannedWordList==null) {
					bannedWordList = QueryBannedWord.getRandomBannedWordList();
				}
			}
		}else {
			return;
		}
	}

}
