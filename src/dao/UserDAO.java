package dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Bean.User;
import util.DAOException;
import util.DBConnection;
import util.UserExist;
import util.UserNotFound;

public class UserDAO {

	public UserDAO() throws ClassNotFoundException, DAOException {
		// TODO Auto-generated constructor stub
		try {
			conn=DBConnection.getInstance().getConnection();
		}
		catch(SQLException e) {
			throw new DAOException("connection cant Establish");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void createUser(User user) throws UserExist, DAOException {
		String qry;
		//InventoryItem item=null;
		try {
			if((getUser(user.getUsername())!=null)){
				throw new UserExist("user is already try new username");
			}	
		}
		catch(UserNotFound e) {
			try {
				int n;
				Statement stmt=conn.createStatement();
				qry= "INSERT INTO user(username,email,password) VALUES('" + user.getUsername() + "','" + user.getEmail() + "','" + user.getPassword() + "');";
				n = stmt.executeUpdate(qry);
			}
			catch(SQLException e1) {
				throw new DAOException(e1.getMessage());
			}
		}
	}

	public void updateUserProfile(User user) throws UserNotFound,DAOException,NullPointerException {
		String qry;
		try {
			Statement stmt=conn.createStatement();
			if(getUser(user.getUsername())==null) {
				throw new UserNotFound("No User found with name"+user.getUsername());
			}else {	
				qry= "update user set username='"+user.getUsername()+
						"',password='"+ user.getPassword() +
						"', email='"+ user.getEmail()+
						"' where username='"+user.getUsername()+"')"; 
				int n=0;
				stmt=conn.createStatement();
				n=stmt.executeUpdate(qry);
			}
		}
		catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	public static User getUser(String username,String pwd) throws UserNotFound, DAOException ,NullPointerException{
		String qry="select * from user where username='"+username+"' and password='"+pwd+"'";
		User user=null;
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			if(!rs.next()) {
				throw new UserNotFound("user name or password is incorrect");
			}
			else {
				user=new User(rs.getString("username"),rs.getString("password"),rs.getString("email"));
				//return user;
			}
		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return user;
	}
	public void removeUSer(User us) throws DAOException {
		String sql="delete from user where username='"+us.getUsername()+"'";
		int n=0;
		try {	 Statement stmt=conn.createStatement();
		n=stmt.executeUpdate(sql);
		}
		catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	private User getUser(String username) throws UserNotFound, DAOException {
		String qry="select * from user where username='"+username+"'";
		User user=null;
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			if(!rs.next()) {
				throw new UserNotFound("user name or password is incorrect");
			}
			else {
				user=new User(rs.getString("username"),rs.getString("password"),rs.getString("email"));
				//return user;
			}
		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return user;
	}
	public User[] getUsers() throws UserNotFound, DAOException{
		String qry="select * from user";
		User user[]=new User[0];
		ArrayList<User> usr = new ArrayList<User>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			int i=0;
			while(rs.next()) {
				//user[i]=new
				usr.add(new User(rs.getString(1),rs.getString(3), rs.getString(2)));
				i++;
				System.out.println(i);
			}
		}catch(SQLException e) {
			throw new DAOException("ERROR" + e.getMessage());
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
		return usr.toArray(user);
	}
	static Connection conn;
}
