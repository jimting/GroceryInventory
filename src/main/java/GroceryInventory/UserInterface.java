package GroceryInventory;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@FeignClient(name="api-gateway")
public interface UserInterface {
	
	/**
	 * 綁定 api-gateway 服務名稱的 XXXXX 接口
	 * @return
	 * 
	 * */

	@RequestMapping(value = "/service-provider2/getData", method = RequestMethod.GET)
	public String getData2();
	
}
