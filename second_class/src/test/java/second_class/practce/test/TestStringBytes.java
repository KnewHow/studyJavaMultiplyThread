package second_class.practce.test;

import java.util.Arrays;

import org.junit.Test;

import practice.createFile.CreateWordJob;
import practice.createFile.WriteWordToTargerFileJob;

public class TestStringBytes {
	
	/**
	 * A alphabet is one byte,and a Chinese character
	 * is three bytes,coded by utf-8
	 * 
	 * English word average length is five
	 * Chinese word average length is  four
	 * 
	 * so the average bytes of each words is (5*1+4*3)/2=8.5 bytes
	 * 
	 * So if we want to get 8.5MB file we need to 8.5*1000*1000
	 * 
	 */
	@Test
	public void testStringBytes() {
		String str="袁";
		System.out.println(str.getBytes().length);
		System.out.println(Arrays.toString(str.getBytes()));
	}
	
	@Test
	public void testCreateFile() {
		String filePath = this.getClass().getResource("/createFile/1.temp").getPath();
		System.out.println(filePath);
		CreateWordJob job = new CreateWordJob();
		job.run();
	}
	
	@Test
	public void testWriteTargetFile() {
		String filePath = this.getClass().getResource("/createFile/target.txt").getPath();
		WriteWordToTargerFileJob job = new WriteWordToTargerFileJob(filePath);
		job.run();
	}
}
