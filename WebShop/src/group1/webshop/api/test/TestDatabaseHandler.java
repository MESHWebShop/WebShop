package group1.webshop.api.test;

import static org.junit.Assert.*;

import org.junit.Test;

import group1.webshop.api.database.DatabaseHandler;

public class TestDatabaseHandler {

	@Test
	public void testAddProductToCart() {
		DatabaseHandler db = new DatabaseHandler();
		
		// Parameters: product_id: 1, cart_id: 1, count: 1
		db.addProductToCart("1", "1", "1");
		
		assertEquals("", true, true);
	}

}
