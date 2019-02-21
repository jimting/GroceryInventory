package GroceryInventory;

import static org.junit.Assert.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class GroceryInventoryTest 
{
	private GroceryInventoryController groceryInventory = new GroceryInventoryController();
	
	//controller's test
	
	@Test
	//index
	public void testIndex()
	{
		assertEquals("success", groceryInventory.index());
	}
	
	@Test
	public void testGetGrocery()
	{
		assertTrue(isJSONValid(groceryInventory.getGrocery()));
	}

	@Test
	public void testGetGroceryByID()
	{
		String ID = "5c49e70e212d8d18c0fccd55";
		assertTrue(isJSONValid(groceryInventory.getGroceryByID(ID)));
	}
	
	@Test
	public void testGetNotification()
	{
		assertTrue(isJSONValid(groceryInventory.getNotification("1")));
	}
	
	
	
	public boolean isJSONValid(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	        // edited, to include @Arthur's comment
	        // e.g. in case JSONArray is valid as well...
	        try {
	            new JSONArray(test);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}
}
