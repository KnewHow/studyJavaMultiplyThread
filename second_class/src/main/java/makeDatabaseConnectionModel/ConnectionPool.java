package makeDatabaseConnectionModel;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * A simple database connection pool
 * @author Yuanguohao
 *
 * @date Nov 6, 2017 7:17:15 PM
 *
 */
public class ConnectionPool {
	
	private static LinkedList<Connection> pool = new LinkedList<>();

	public ConnectionPool(int poolSize) {
		for(int i=0;i<poolSize;i++) {
			pool.addLast(ConnectionDriver.createConnection());
		}
	}
	
	/**
	 * Release database connection:put the connection to
	 * the last index of queue
	 * @param connection
	 */
	public void releaseConnection(Connection connection) {
		synchronized (pool) {
			if(connection!=null) {
				pool.addLast(connection);
				//notify threads witch is wait for connection
				pool.notifyAll();
			}
		}
	}
	
	/**
	 * Get connection from pool,if the thread don't get connection
	 * in certain time,<code>null</code> will be returned.
	 * @param mills The time the thread will wait to get connection.
	 * @return A {@linkplain Connection} or <code>null</code>
	 * @throws InterruptedException 
	 */
	public Connection fetchConnection(long mills) throws InterruptedException {
		synchronized (pool) {
			//out time 
			if(mills<0) {
				while(pool.isEmpty()) {
					pool.wait();
				}
				return pool.removeFirst();
			}else {
				//The time the thread will wait to
				long future = System.currentTimeMillis()+mills;
				//The time the thread left waiting
				long remaining = mills;
				while(pool.isEmpty() && remaining>0) {
					pool.wait();
					remaining = future-System.currentTimeMillis();
				}
				/*
				 * don't wait when time is out,in current time,if the
				 * pool is not empty,return connection,otherwise return null
				 */
				Connection result = null;
				if(!pool.isEmpty()) {
					result = pool.removeFirst();
				}
				return result;
			}
		}
	}
	
	
}
