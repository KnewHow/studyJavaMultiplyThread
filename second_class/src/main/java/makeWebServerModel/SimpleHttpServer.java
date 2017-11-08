package makeWebServerModel;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple http server model
 * @author Yuanguohao
 *
 * @date Nov 8, 2017 8:14:41 PM
 *
 */
public class SimpleHttpServer {
	public static ThreadPool threadPool = new DefaultThreadPool(10);
	
	public static String basePath;
	
	public static ServerSocket serverSocket;
	
	public static int port=8080;
	
	public static void setPort(int port) {
		if(port>0) {
			SimpleHttpServer.port = port;
		}
	}
	
	public static void setBasePath(String basePath) {
		if(basePath!=null && new File(basePath).exists()
				&& new File(basePath).isDirectory()) {
			SimpleHttpServer.basePath = basePath;
		}
	}
	
	/**
	 * start Http server
	 * @throws IOException
	 */
	public static void start() throws IOException {
		serverSocket = new ServerSocket(port);
		Socket socket = null;
		while((socket=serverSocket.accept())!=null) {
			threadPool.execute(new HttpRequestHandler(socket, basePath));
		}
		serverSocket.close();
	}
}
