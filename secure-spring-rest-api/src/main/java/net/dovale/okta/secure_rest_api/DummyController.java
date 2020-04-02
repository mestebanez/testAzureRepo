package net.dovale.okta.secure_rest_api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.dovale.okta.secure_rest_api.payload.HelloPayload;

@RestController
public class DummyController implements ErrorController {

	//@Value("${dum}")
	private String testVal;
	
	//@Autowired
	//private MeterRegistry reg;
	
	@Autowired
	private HttpServletRequest request;
	/*
    @RequestMapping("/error")
    public String handleError() {
    	
    	String auth = request.getHeader("Authorization");
    	
    	System.out.println("Bearer : " + auth.split("Bearer:")[1]);
    	Timer timer = reg.timer("mad timer");
    	
    	timer.record(Duration.ofMillis(3000));
        //do something like logging
    	return null;
    }*/
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
    
    
    @PostMapping("/hello1")
    public String postHello(@RequestBody HelloPayload map)
    {
    	return map + "Returned";
    }
    
    @GetMapping("/hello123")
   // @PreAuthorize("#oauth2.hasScope('custom_mod')")
    public String helloWorld( HttpServletRequest request)throws Exception{
    	
    	if (request.getParameter("error")!=null)
    	{
    		throw new HttpExceptionResponse();
    	}
    	
	    return "harra";
    }
    
}
