package makeWebServerModel;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Http request handler
 * 
 * @author Yuanguohao
 *
 * @date Nov 8, 2017 7:56:23 PM
 *
 */
public class HttpRequestHandler implements Job {

	private Socket socket;

	private String basePath;

	public HttpRequestHandler(Socket socket, String basePath) {
		super();
		this.socket = socket;
		this.basePath = basePath;
	}

	@Override
	public void run() {
		String line = null;
		BufferedReader br = null;
		BufferedReader reader = null;
		PrintWriter out = null;
		InputStream in = null;
//		ThreadUtils.sleepSeconds(10);
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String head = reader.readLine();
			String filePath = this.basePath + head.split(" ")[1];
			out = new PrintWriter(socket.getOutputStream());
			if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
				in = new FileInputStream(filePath);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int i = 0;
				while ((i = in.read()) != -1) {
					baos.write(i);
				}
				byte[] array = baos.toByteArray();
				out.println("HTTP/1.1 200 OK");
				out.println("Server:MyDefault");
				out.println("Content-Type:image/jpeg");
				out.println("Content-length:" + array.length);
				out.println("");
				socket.getOutputStream().write(array, 0, array.length);
			} else {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
				out = new PrintWriter(socket.getOutputStream());
				out.println("HTTP/1.1 200 OK");
				out.println("Server:MyDefault");
				out.println("Content-Type:text/html;charset=UTF-8");
				out.println("");
				while ((line = br.readLine()) != null) {
					out.println(line);
				}
				out.flush();
			}
		} catch (Exception e) {
			out.println("HTTP/1.1 500");
			out.println("");
			out.flush();
		} finally {
			close(br, in, reader, out, socket);
		}
	}

	private static void close(Closeable... closeables) {
		if (closeables != null) {
			for (Closeable c : closeables) {
				try {
					c.close();
				} catch (Exception e) {
				}
			}
		}
	}

}
