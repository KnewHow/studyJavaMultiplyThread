package makeWebServerModel;

import java.io.IOException;

public class TestClass {
	public static void main(String[] args) {
		try {
			String resource = Thread.currentThread().getClass().getResource("/").getPath();
			System.out.println(resource);
			String basePath = "/project/studyJavaMultiply/workspace/second_class/target/classes/makeWebServerModel";
			SimpleHttpServer.setBasePath(basePath);
			SimpleHttpServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
