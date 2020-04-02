package net.dovale.okta.secure_rest_api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

	@Value("${okta.oauth2.issuer}")
	private String iss;
	
	@PostConstruct
	public void post()
	{
		System.out.println("Read from app.properties "  + iss);
	}
	
}