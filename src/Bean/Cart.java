package Bean;

import util.InSufficientStock;

public class Cart 
{  
	public Cart() {
		setCart(0);
		setUsername(null);
	}
	public Cart(int cartId,String uname){ 
		///setCode;
		setCart(cartId);
		setUsername(uname);
	}  
	public int getCart() {
		return cart;
	}
	public void setCart(int cart) {
		this.cart = cart;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	private int cart;
	private String username; 
	
} 