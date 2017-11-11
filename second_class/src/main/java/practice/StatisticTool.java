package practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Yuanguohao
 *
 * @date Nov 11, 2017 7:30:45 PM
 *
 */
public class StatisticTool {
	
	
	/**
	 * statistic a text file regex appear times
	 * @param text The content of text
	 * @param regex The word will statistic
	 * @return The times the word appear times
	 */
	public static int statistic(String text, String regex) {
		int times = 0;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			times++;
		}
		
		return times;
	}
	
	
}
