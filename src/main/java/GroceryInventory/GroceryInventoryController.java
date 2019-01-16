package GroceryInventory;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.ServerAddress;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.model.Filters.*;





@RestController
public class GroceryInventoryController {
	@CrossOrigin(origins = "*")
	@RequestMapping("/")
    public String index() 
    {
		
		return GroceryInventory.getGrocery("1");
		
        //return "success";
    }
	
	
	@RequestMapping(value = "getCinemaCatalog", method = RequestMethod.GET)
    public String getCinemaCatalog(@RequestParam("userID") String userID)
    {
    	return GroceryInventory.getGrocery(userID);
    }
	
	SingleResultCallback<Document> printDocument = new SingleResultCallback<Document>() {
	    @Override
	    public void onResult(final Document document, final Throwable t) {
	        System.out.println(document.toJson());
	    }
	};
	
}



