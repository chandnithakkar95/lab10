package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import util.InSufficientStock;
import Bean.Cart;
import Bean.CartItem;
import Bean.InventoryItem;
import util.DAOException;
import util.DBConnection;
import util.ItemNotFound;
import util.ItemExists;

public class CartItemDAO {
	private Connection conn;

	public CartItemDAO() throws DAOException  {
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
	public void addCartItem(CartItem crtItm)
			throws ItemExists, DAOException {
		String qry;
		System.out.println("hiii");

		try {

			Statement stmt=conn.createStatement();			
			int n;
			qry= "INSERT INTO cart_item(cart_item_id,inventory_item_id,Quantity,cart_id) VALUES("
					+crtItm.getCartItemCode()+ ","+crtItm.getInventoryItemCode()+","+crtItm.getQuantity()+","+crtItm.getCart()+");";
			n = stmt.executeUpdate(qry);
		}
		catch(SQLException e1) {
			throw new DAOException(e1.getMessage());
		}
	}
	public int getLastShoppingItems() throws DAOException {
		String qry="select * from cart_item;";
		//ArrayList<InventoryItem> item;
		CartItem items[]=new CartItem[0];
		ArrayList<CartItem> im=new ArrayList<CartItem>();
		Integer[] itmindex=new Integer[0];
		ArrayList<Integer> titmindex=new ArrayList<Integer>();
		int tmp; 
		try {

			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			int i=0;
			if(!rs.next()) {

				return 0;
			}else {
				while (rs.next()) {
					titmindex.add(rs.getInt(1));
				}
				itmindex=titmindex.toArray(itmindex);
				Arrays.sort(itmindex);
				System.out.println(itmindex.length);
				 tmp=itmindex[itmindex.length];
				
			}
		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		items=im.toArray(items);
		return tmp;
	}
	/*public void InsertItems(InventoryItem[] items)
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
	}*/
	/*public void updateCart(Cart items) throws ItemNotFound,DAOException {
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
	}*/
	public CartItem getCartItem(CartItem item) throws ItemNotFound, DAOException {
		String qry="select * from cart_item where intventory_item_id="+item.getInventoryItemCode() +"and cart_id="+item.getCart();
		CartItem item1=null;
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			if(!rs.next())
				throw new ItemNotFound("cart not found");
			else
				item1=new CartItem(rs.getInt(1),
						rs.getInt(2),rs.getInt(3),rs.getInt(4));
		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return item;
	}
	public CartItem[] getCartItems(int cartId) throws DAOException {
		String qry="select * from cart_item where cart_id="+cartId+";";
		//ArrayList<InventoryItem> item;
		CartItem items[]=new CartItem[0];
		ArrayList<CartItem> im=new ArrayList<CartItem>();
		try {	
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			int i=0;
			while(rs.next()) {
				im.add(new CartItem(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4)));
			}
		}catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		items=im.toArray(items);
		return items;
	}

	public void deleteItem(int cartId) throws ItemNotFound, DAOException {
		String sql="delete from shopping_cart where cart_idt="+cartId;
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
	public double totalInventoryCost(int cartId) throws DAOException {
		CartItem[] items=getCartItems(cartId);
		InventoryDAO dao=new InventoryDAO();
		double cost=0.0;
		for(int i=0;i<items.length;i++) {
			InventoryItem invitem;
			try {
				invitem = dao.getItem(items[i].getInventoryItemCode());
				cost+=items[i].getQuantity()*invitem.getCost();
			} catch (ItemNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cost;
	}
	public Cart[] getPaginatedItems(int page_no) throws DAOException {
		ArrayList<Cart> item_list = new ArrayList<Cart>();
		Cart[] i=new Cart[0];
		Statement stmt;
		try {
			stmt = conn.createStatement();        
			//use offset and limit to return required page
			int offset = (page_no-1)*getPage_length();
			String sql = "SELECT * shopping_cart limit " + getPage_length() + " offset " + offset;
			ResultSet rs = stmt.executeQuery( sql );
			while ( rs.next() ) {
				item_list.add(new Cart(
						rs.getInt(1),
						rs.getString(2)
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