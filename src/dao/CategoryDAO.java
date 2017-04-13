package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Bean.Category;
import Bean.InventoryItem;
import util.DAOException;
import util.DBConnection;

public class CategoryDAO {
	private Connection conn;

	public CategoryDAO() throws DAOException {
		try {
			conn=DBConnection.getInstance().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("SQL error");
		}
	}
	public Category getcategory(int cate_id) throws CateNotFound, DAOException {
		String qry="select * from category where category_id="+cate_id;
		Category category=null;
		try {

			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			if(!rs.next())
				throw new CateNotFound("category not found");

			else
				category=new Category(rs.getInt("category_id"),
						rs.getString("category"));
		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return category;
	}
	public Category[] getCategories() throws DAOException {
		String qry="select * from category;";
		//ArrayList<InventoryItem> category;
		Category categories[]=new Category[0];
		ArrayList<Category> im=new ArrayList<Category>();
		try {	
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			int i=0;
			while(rs.next()) {
				im.add(new Category(
						rs.getInt("category_id"),
						rs.getString("category")));
			}

		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		categories=im.toArray(categories);
		return categories;
	}
}
