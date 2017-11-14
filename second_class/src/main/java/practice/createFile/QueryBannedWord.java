package practice.createFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryBannedWord {

	/**
	 * Get banned word
	 * 
	 * @return
	 */
	public static List<String> getRandomBannedWordList() {

		FileReader fileReader = null;
		List<String> bannedWordList = new ArrayList<String>();
		BufferedReader bufferedReader = null;
		try {
			String filePath = QueryBannedWord.class.getResource("/practice/bannedWords.txt").getPath();
			fileReader = new FileReader(new File(filePath));
			bufferedReader = new BufferedReader(fileReader);
			String temp = "";
			String str = null;
			while ((str=bufferedReader.readLine())!=null) {
				temp += str;
			}
			String[] strs = temp.split(",");
			for (int i = 0; i < strs.length; i++) {
				bannedWordList.add(strs[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fileReader!=null) {
					fileReader.close();
				}
				if(bufferedReader!=null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bannedWordList;
	}
}
