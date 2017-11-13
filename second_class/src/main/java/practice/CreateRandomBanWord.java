package practice;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class CreateRandomBanWord {

	private static final Integer BANNED_WORD_SIZE = 100;

	
	public static void main(String[] args) throws Exception {
		String pathName = CreateRandomBanWord.class.getClassLoader().getResource("practice/prohibitedWords.properties").getPath();
		File file = new File(pathName);
		Writer writer = new FileWriter(file);
		writer.write("prohibiten_word=");
		writer.write(getRandomBannedWord());
		writer.flush();
		writer.close();
	}
	
	public static String getRandomBannedWord() {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		while (counter < 100) {
			String str = CreateWordHelper.createRandomChinesWord();
			if (str != null && str.length() != 0) {
				sb.append(str);
				counter++;
				if(counter!=BANNED_WORD_SIZE-1) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
}
