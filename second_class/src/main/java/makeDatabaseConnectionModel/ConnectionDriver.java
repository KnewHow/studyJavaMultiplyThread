package makeDatabaseConnectionModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import threadUtil.ThreadUtils;

/**
 * The driver of database to create database connection
 * @author Yuanguohao
 *
 * @date Nov 6, 2017 7:19:58 PM
 *
 */
public class ConnectionDriver {
	/**
	 * Just to demo,we use JDK dynamic proxy to construct
	 * a simple database connection.
	 * @author Yuanguohao
	 *
	 * @date Nov 6, 2017 7:24:24 PM
	 *
	 */
	static class ConnectionHandler implements InvocationHandler{
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if("commit".equals(method.getName())) {
				ThreadUtils.sleepMillSeconds(100);;
			}
			return null;
		}
		
	}
	
	/**
	 * Get database connection from simple JDK dynamic proxy
	 * @return
	 */
	public static Connection createConnection() {
		Object proxy = Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
				new Class<?>[] {Connection.class}, 
				new ConnectionHandler());
		return (Connection) proxy;
	}
}
