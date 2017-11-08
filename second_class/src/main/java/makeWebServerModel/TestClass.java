package makeWebServerModel;

import java.io.IOException;

public class TestClass {
	public static void main(String[] args) {
		try {
			String basePath = "/project/studyJavaMultiply/workspace/second_class/target/classes/makeWebServerModel";
			SimpleHttpServer.setBasePath(basePath);
			SimpleHttpServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
