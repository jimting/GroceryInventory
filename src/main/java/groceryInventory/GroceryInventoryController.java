package groceryInventory;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class GroceryInventoryController 
{
    
    @RequestMapping("/")
    public String index() 
    {
        return "安安安安";
    }
    
    @RequestMapping(value = "getGrocery", method = RequestMethod.GET)
    public String getGrocery(@RequestParam("userID") String userID)
    {
    	return GroceryInventory.getGrocery(userID);
    }
  
}
