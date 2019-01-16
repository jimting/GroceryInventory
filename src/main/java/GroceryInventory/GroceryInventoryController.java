package GroceryInventory;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class GroceryInventoryController {
	@CrossOrigin(origins = "*")
	@RequestMapping("/")
    public String index() 
    {
		return "success";
    }
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getGrocery", method = RequestMethod.GET)
    public String getGrocery(@RequestParam("userID") String userID)
    {
    	return GroceryInventory.getGrocery(userID);
    }
	
}



