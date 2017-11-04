package showWaitCondition;

public class ShowWaitCondition extends ShowThreadLocal{

	public static void main(String[] args) {
		begin();
		System.out.println(new ShowWaitCondition().get(5000L));
		System.out.println("cast "+end()+" mills");
	}
	public synchronized Object get(Long i) {
		Object result = null;
		Long future = System.currentTimeMillis()+i;
		Long remainMills = i;
		while((result==null) && remainMills>0) {
			try {
				this.wait(remainMills);
				remainMills = future-System.currentTimeMillis();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
}
