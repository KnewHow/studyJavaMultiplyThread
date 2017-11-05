package threadCommunication_PipedConnect;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * In this case,we will use input piped and output piped to let two thread
 * communication
 * 
 * In this case,main method will take act as writer and another will take act as
 * reader.They will communication by piped stream
 * 
 * @author Yuanguohao
 *
 * @date Nov 1, 2017 7:15:17 PM
 *
 */
public class ShowPipedCommunication {

	public static void main(String[] args) {
		PipedReader pipedReader = new PipedReader();
		PipedWriter pipedWriter = new PipedWriter();
		try {
			pipedWriter.connect(pipedReader);
			Thread thread = new Thread(new PipedReaderThread(pipedReader), "PipedReader");
			thread.start();
			int recever = 0;
			while ((recever = System.in.read()) != -1) {
				pipedWriter.write(recever);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				pipedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @author Yuanguohao
	 *
	 * @date Nov 1, 2017 7:23:24 PM
	 *
	 */
	static class PipedReaderThread implements Runnable {

		private PipedReader pipedReader = null;

		public PipedReaderThread(PipedReader pipedReader) {
			super();
			this.pipedReader = pipedReader;
		}

		@Override
		public void run() {
			if (pipedReader != null) {
				int recever = 0;
				try {
					while ((recever = this.pipedReader.read()) != -1) {
						System.out.print((char) recever);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
