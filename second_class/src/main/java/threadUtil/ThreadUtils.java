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
}
