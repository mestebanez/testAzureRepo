package net.dovale.okta.secure_rest_api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@SpringBootApplication
//@EnableDiscoveryClient
@ComponentScan(basePackageClasses={net.dovale.okta.secure_rest_api.DummyController.class})
//@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecureRestApiApplication  {

	
	public static void main(String[] args) {
		SpringApplication.run(SecureRestApiApplication.class, args);
	}
	
	/*@Bean
	public PropertySourcesPlaceholderConfigurer init(ApplicationContext cntxt)
	{
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		properties.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
		  properties.setIgnoreResourceNotFound(Boolean.TRUE);
	    MutablePropertySources sources = new MutablePropertySources();
	    CustomPropertySources propertySource = new CustomPropertySources("my custom property source");
	    sources.addLast(propertySource);
	    properties.setPropertySources(sources);
	    return properties;
	}
	
	*//** This is needed for some reason. When we add the above property source we cancel out the default one. Ahhh!!!*//*
	@Bean
	public PropertySourcesPlaceholderConfigurer init2(ApplicationContext cntxt) throws Exception
	{
		AnnotationConfigServletWebServerApplicationContext ctxt = (AnnotationConfigServletWebServerApplicationContext)cntxt;
		ctxt.isActive();
		
		Properties props = new Properties();
		props.load(SecureRestApiApplication.class.getClassLoader().getResourceAsStream("application.properties"));
		
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		 properties.setProperties(props);
	    return properties;
	}*/


}

