package hello;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "安安安安";
    }
    
    @RequestMapping("/page1")
    public String page1() {
        return "Hello! This is Page1!";
    }
    
    @RequestMapping("/weather") 
    public String weather(){
    	return "今天天氣很好!";
    }
    
    @GetMapping(value = "/count/{a}&{b}") 
    public int count(@PathVariable int a, @PathVariable int b)
    {
    	return Hello.add(a, b);
    }
    
    @RequestMapping("/test") 
    void test(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException 
    {
    	RequestDispatcher view;
    	view = request.getRequestDispatcher("page.html");
		view.forward(request, response);
    }
    
    
    
}
