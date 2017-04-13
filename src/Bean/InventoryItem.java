package Bean;

import util.InSufficientStock;

public class InventoryItem 
{  
	public InventoryItem() {
		this.setCateId(0);
		setCode(0);
		setCost(0.0);
		setDescription("");
		setStock(0);
		setMinStock(0);
	}
	public InventoryItem(int itm_code, String itm_description, int qty, int min_qty, double cost,int category_id
			){ 
		///setCode;
		this.setCode(itm_code);
		this.setDescription(itm_description);
		this.qty_in_stock=qty;
		this.min_required_stock=min_qty;
		this.setCost(cost);
		this.setCateId(category_id);
	}  
	public InventoryItem (int code,String description,double cost){ 
		//sets qty and min_qty to zero
		this.setCode(code);
		this.setDescription(description);
		this.setCost(cost);
	}  
	public void addStock(int qty){ 
		//increases the stock by given amount
		this.qty_in_stock+=qty;
	}  
	//public int getQty(){return qty_in_stock; }  
	
	public Boolean isUnderStock(){
		return qty_in_stock<min_required_stock?true:false;
	}  
	public void withdrawStock(int qty)throws InSufficientStock {
	if (qty_in_stock>=qty)
		qty_in_stock -= qty;
	else
		throw new InSufficientStock("Stock is Un sufficient"); 
	}  
	public int getCode() {
		return item_code;
	}
	public void setCode(int item_code) {
		this.item_code = item_code;
	}
	public String getDescription() {
		return item_description;
	}
	public void setDescription(String item_description) {
		this.item_description = item_description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	//Field Declarations 
	private int category_id;
	
	private int item_code; 
	private String item_description; 
	private int qty_in_stock; 
	private int min_required_stock; 
	private double cost;
	public int getStock() {
		return qty_in_stock;
	}
	public void setStock(int qty_in_stock) {
		this.qty_in_stock = qty_in_stock;
	}
	public int getMinStock() {
		return min_required_stock;
	}
	public void setMinStock(int min_required_stock) {
		this.min_required_stock = min_required_stock;
	}
	public int getCateId() {
		return category_id;
	}
	public void setCateId(int category_id) {
		this.category_id = category_id;
	} 
	public String toString() {
		// TODO Auto-generated method stub
		
		return "Item Code: "+getCode()+"\nName"+getDescription()+"\n Qty:"+getStock()+"\n Min Qty:"+getMinStock()+"\n Cost:"+getCost();
	}
} 