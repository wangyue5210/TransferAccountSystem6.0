package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.jasper.tagplugins.jstl.core.Set;



public class DBUtil {
	
	private DBUtil(){}
	
	static {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	private static final String url="jdbc:mysql://localhost:3306/training?serverTimezone=UTC&characterEncoding=UTF-8";
	private static final String user="root";
	private static final String password="root";
	
	private static  ThreadLocal<Connection> t=new ThreadLocal<Connection>();
	
	
	public static Connection getConnection() throws SQLException {
		
		Connection conn =t.get(); 
		if (conn==null) {
			conn=DriverManager.getConnection(url, user, password);
			t.set(conn);
		}
		return conn;
	}
	
	public static void DBClose(Connection conn,PreparedStatement ps,ResultSet rs) throws SQLException {
		
		if (rs!=null) {
			rs.close();
			
		}
		if (ps!=null) {
			ps.close();
			
		}
		if (conn!=null) {
			conn.close();
			t.remove();//由于线程执行完之后是继续放到线程池而不是自动销毁，所以必须手动移除
			
		}
	} 
	
	

}
