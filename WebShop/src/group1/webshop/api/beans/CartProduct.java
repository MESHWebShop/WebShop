package group1.webshop.api.beans;

import java.util.Arrays;

/**
 * Product bean class
 */
public class CartProduct extends Product {

    private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public CartProduct() { }

}
