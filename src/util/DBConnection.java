package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private static String driverClassName = "com.mysql.jdbc.Driver";
	private static String connectionUrl = "jdbc:mysql://localhost:3306/mydb";
	private static String userName = "root";
	private static String password = "chandu";
	private static Connection conn = null;

	public DBConnection() throws SQLException, ClassNotFoundException{
		// TODO Auto-generated constructor stub
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(connectionUrl, userName,password);
			
	}
	public static  DBConnection getInstance() throws ClassNotFoundException, SQLException {
		
		if (instance == null)
			instance=new DBConnection();
		return instance;
	}
	public static Connection getConnection()  {
		if(conn==null) {
			System.out.println("connection not done");
		}
		else {
			System.out.println("connection success");
		}
		return conn;
	}
	public void close() throws SQLException {
		conn.close();
		instance=null;
	}
	
		private static DBConnection instance;
	public Connection getConnectionSchema(String s) throws SQLException {
		//String schema="inventory_item";
		Statement st = conn.createStatement();
        st.execute("set search_path to " + s + ";");
        return conn;
	}
}
