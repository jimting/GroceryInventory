package GroceryInventory;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name="api-gateway")
public interface FeignInterface {
	
	/**
	 * 綁定 api-gateway 服務名稱的 XXXXX 接口
	 * @return
	 * 
	 * */

	@RequestMapping(value = "/notification/getNotification", method = RequestMethod.GET)
    public String getNotification(@RequestParam("userID") String userID);
	
	@RequestMapping(value = "/ordering/newGroceryOrdering", method = RequestMethod.GET)
    public String orderingGrocery(@RequestParam("groceryID") String groceryID, @RequestParam("quantity") String quantity);
	
	@RequestMapping(value = "/ordering/getGroceryFromOrderList", method = RequestMethod.GET)
    public String getGroceryFromOrderList(@RequestParam("userID") String userID);
	
}
