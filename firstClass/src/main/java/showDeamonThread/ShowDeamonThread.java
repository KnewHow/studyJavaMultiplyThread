package showDeamonThread;

/**
 * 
 * @author Yuanguohao
 *
 * @date Oct 30, 2017 8:17:01 PM
 *
 */
public class ShowDeamonThread {

	
	public static void main(String[] args) {
		Thread thread = new Thread(new DeamonThread(),"deamonThread");
		thread.setDaemon(true);
		thread.start();
	}
	static class DeamonThread implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				System.out.println("deamon finally code excuting");
			}
		}
		
	}
}
