package threadUtil;

import java.util.concurrent.TimeUnit;

public class ThreadUtils {

	public static void sleepSeconds(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void sleepMillSeconds(long millSeconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(millSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
