package second_class.practce.test;

import org.junit.Test;

import practice.CreateTargetFile;
import practice.StatisticTool;

public class FindWordAppearTimeTest {

	
	@Test
	public void testStatisticWord() {
		String text = "用工荒jav用工荒asd用工荒sd用工dsddsdawqwqdJavaJavadsdjaveweeJavajava";
		String regex="用工荒";
		int times = StatisticTool.statistic(text, regex);
		System.out.println(times);
	}
	
	/**
	 * 随机生成一个汉字
	 */
	@Test
	public void testCreateChinesRandom() {
		for(int i=0;i<100;i++) {
			char ch =  (char)(0x4e00+(int)(Math.random()*(0x9fa5-0x4e00)));
			System.out.println(ch);
		}
	}
	
	@Test
	public void testCreateWordRandom() {
		CreateTargetFile.createRandomWord();
	}
}
