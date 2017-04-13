package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.InSufficientStock;
import Bean.InventoryItem;
import util.DAOException;
import util.DBConnection;
import util.ItemNotFound;
import util.ItemExists;

public class InventoryDAO {
	private Connection conn;

	public InventoryDAO() throws DAOException  {
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
	public void InsertItem(InventoryItem item)
			throws ItemExists, DAOException {
		String qry;
		System.out.println("hiii");
		try {
			if(getItem(item.getCode())!=null) {
				throw new ItemExists("item is already exist");
			}
		}
		catch(ItemNotFound e) {
			try {
				
				Statement stmt=conn.createStatement();			
				int n;
				qry= "INSERT INTO inventory_item (itm_code,item_decription,qty,min_stock,cost,cate_id) VALUES(" +
						"'" + item.getCode() + "', " +
						"'" + item.getDescription() + "', " +
						item.getStock()+ ", " +
						item.getMinStock() + ","+
						item.getCost()+ ","+item.getCateId()+"); ";

				n = stmt.executeUpdate(qry);
			}
			catch(SQLException e1) {
				throw new DAOException(e1.getMessage());
			}
		}		
	}
	public void InsertItems(InventoryItem[] items)
			throws ItemExists, DAOException {		//should commit only if all items are successfully inserted otherwise should rollback
		String qry;
		try {
			conn.setAutoCommit(false);
			Statement stmt=conn.createStatement();			
			for(int i=0;i<items.length;i++) {
				if(getItem(items[i].getCode())!=null)
					throw new ItemExists("item is already exist");
				int n;
				qry= "INSERT INTO inventory_item (itm_code,item_decription,qty,min_stock,cost,cate_id) VALUES(" +
						"'" + items[i].getCode() + "', " +
						"'" + items[i].getDescription() + "', " +
						items[i].getStock()+ ", " +
						items[i].getMinStock() + ","+
						items[i].getCost()+ ","+items[i].getCateId()+"); ";

				n = stmt.executeUpdate( qry );
			}
			conn.commit();
		}catch(SQLException|ItemNotFound e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DAOException(e.getMessage());
		}
		finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void updateItem(InventoryItem items) throws ItemNotFound,DAOException {
		String qry;
		try {
			Statement stmt=conn.createStatement();
			if(getItem(items.getCode())==null) {
				throw new ItemNotFound("item not found");
			}else {	
				qry= "update inventory_item set item_decription='"+ items.getDescription() +
						"', qty="+ items.getStock()+
						" ,min_stock="+items.getMinStock() +
						" ,cost="+items.getCost() + 
						",cate_id="+items.getCateId()+" where itm_code="+items.getCode(); 
				System.out.println(qry);
				int n=0;
				stmt=conn.createStatement();
				n=stmt.executeUpdate(qry);
			}
		}
		catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	public void upDateItems(InventoryItem[] items)
			throws ItemNotFound, DAOException {
		//should commit only if all items are updated otherwise rollback
		try {
			conn.setAutoCommit(false);
			Statement stmt=conn.createStatement();
			for(int i=0;i<items.length;i++) {
				String qry;

				if(getItem(items[i].getCode())==null) {
					throw new ItemNotFound("item not found");
				}else {	
					qry= "update inventory_item set itm_code="+items[i].getCode()+
							",item_decription='"+ items[i].getDescription() +
							"', qty="+ items[i].getStock()+
							" ,min_stock="+items[i].getMinStock() +
							" ,cost="+items[i].getCost() + 
							",cate_id="+items[i].getCateId()+"where itm_code="+items[i].getCode(); 
					int n=0;
					stmt=conn.createStatement();
					n=stmt.executeUpdate(qry);
				}
			}
			conn.commit();
		}
		catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DAOException(e.getMessage());
		}
		finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public InventoryItem getItem(int item_code) throws ItemNotFound, DAOException {
		String qry="select * from inventory_item where itm_code="+item_code;
		InventoryItem item=null;
		try {

			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			if(!rs.next())
				throw new ItemNotFound("item not found");

			else
				item=new InventoryItem(rs.getInt("itm_code"),
						rs.getString("item_decription"),
						rs.getInt("qty"),
						rs.getInt("min_stock"),
						rs.getDouble("cost"),
						rs.getInt("cate_id"));
		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return item;
	}
	public InventoryItem[] getItems() throws DAOException {
		String qry="select * from inventory_item;";
		//ArrayList<InventoryItem> item;
		InventoryItem items[]=new InventoryItem[0];
		ArrayList<InventoryItem> im=new ArrayList<InventoryItem>();
		try {	
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			int i=0;
			while(rs.next()) {
				im.add(new InventoryItem(
						rs.getInt("itm_code"),
						rs.getString("item_decription"),
						rs.getInt("qty"),rs.getInt("min_stock"),
						rs.getDouble("cost"),rs.getInt("cate_id")
						));
			}

		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		items=im.toArray(items);
		return items;
	}
	public InventoryItem[] getItemsCategory(int cate_id) throws DAOException {
		String qry="select * from inventory_item where cate_id="+cate_id+";";
		//ArrayList<InventoryItem> item;
		InventoryItem items[]=new InventoryItem[0];
		ArrayList<InventoryItem> im=new ArrayList<InventoryItem>();
		try {	
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			int i=0;
			while(rs.next()) {
				im.add(new InventoryItem(
						rs.getInt("itm_code"),
						rs.getString("item_decription"),
						rs.getInt("qty"),rs.getInt("min_stock"),
						rs.getDouble("cost"),rs.getInt("cate_id")
						));
			}

		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		items=im.toArray(items);
		return items;
	}
	public void addStock(int item_code, int qty) throws ItemNotFound, DAOException {
		InventoryItem item=getItem(item_code);
		item.addStock(qty);
		updateItem(item);
	}
	public void withdrawStock(int item_code, int qty) throws ItemNotFound, InSufficientStock, DAOException {
		InventoryItem item=getItem(item_code);
		item.withdrawStock(qty);
		updateItem(item);
	}
	public InventoryItem[] itemsUnderStock() throws DAOException{
		InventoryItem[] items=getItems();
		ArrayList<InventoryItem> itemsUStock=new ArrayList<>();
		InventoryItem[] itemsUnderStock=new InventoryItem[0];
		for(int i=0;i<items.length;i++) {
			if(items[i].isUnderStock()) {
				itemsUStock.add(items[i]);
			}
		}
		itemsUnderStock=itemsUStock.toArray(itemsUnderStock);
		return itemsUnderStock;
	}
	public void deleteItems(int[] items_codes) throws ItemNotFound, DAOException {
		try {
			conn.setAutoCommit(false);
			for(int i=0;i<items_codes.length;i++) {
				String sql="delete from inventory_item where itm_code="+items_codes[i];
				int n=0;
				Statement stmt=conn.createStatement();
				n=stmt.executeUpdate(sql);
			}
		}
		catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DAOException(e.getMessage());
		}
		finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public void deleteItem(int item_code) throws ItemNotFound, DAOException {
		String sql="delete from inventory_item where itm_code="+item_code;
		int n=0;
		try {	 Statement stmt=conn.createStatement();
		n=stmt.executeUpdate(sql);
		}
		catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	public void setPage_length(int page_length) {
		this.page_length = page_length;
	}
	public int getPage_length() {
		return page_length;
	}
	public double totalInventoryCost() throws DAOException {
		InventoryItem[] items=getItems();
		double cost=0.0;
		for(int i=0;i<items.length;i++) {
			cost+=items[i].getCost()*items[i].getStock();
		}
		return cost;
	}
	public InventoryItem[] getPaginatedItems(int page_no) throws DAOException {
		ArrayList<InventoryItem> item_list = new ArrayList<InventoryItem>();
		InventoryItem[] i=new InventoryItem[0];
		Statement stmt;
		try {
			stmt = conn.createStatement();        
			//use offset and limit to return required page
			int offset = (page_no-1)*getPage_length();
			String sql = "SELECT * inventory_item limit " + getPage_length() + " offset " + offset;
			ResultSet rs = stmt.executeQuery( sql );
			while ( rs.next() ) {
				item_list.add(new InventoryItem(
						rs.getInt("itm_code"),
						rs.getString("item_decription"),
						rs.getInt("qty"),rs.getInt("min_stock"),
						rs.getDouble("cost"),
						rs.getInt("cate_id")
						));
			}        
		}
		catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		i=item_list.toArray(i);
		return i;
	}	
	private int page_length = 20;

}	