package Bean;

import util.InSufficientStock;

public class CartItem 
{  
	public CartItem() {
		setCart(Integer.MIN_VALUE);
		setCartItemCode(Integer.MIN_VALUE);
		setInventoryItemCode(Integer.MIN_VALUE);
		setQuantity(Integer.MIN_VALUE);
	}
	public CartItem(int cartid,int iitemCode,int cartItemId){ 
		setCart(cartid);
		setCartItemCode(cartItemId);
		setInventoryItemCode(iitemCode);
		setQuantity(1);
	}  
	
	public CartItem(int iitemCode,int cartid,int qty,int cartItemId){ 
		///setCode;
		setCart(cartid);
		setCartItemCode(cartItemId);
		setInventoryItemCode(iitemCode);
		setQuantity(qty);
	}  

	public int getCart() {
		return cart;
	}
	public void setCart(int cart) {
		this.cart = cart;
	}
	
	private int cartItemCode;
	private int inventoryItemCode;
	private int quantity;
	private int cart;
	public int getCartItemCode() {
		return cartItemCode;
	}
	public void setCartItemCode(int cartItemCode) {
		this.cartItemCode = cartItemCode;
	}
	public int getInventoryItemCode() {
		return inventoryItemCode;
	}
	public void setInventoryItemCode(int inventoryItemCode) {
		this.inventoryItemCode = inventoryItemCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
} 