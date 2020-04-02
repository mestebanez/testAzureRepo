package net.dovale.okta.secure_rest_api;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class AppListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		System.out.println("hit!!!");
		
		if (arg0 instanceof ApplicationContextEvent)
		{
			//((ConfigurableApplicationContext)((ApplicationContextEvent)arg0.getSource()).getApplicationContext()).getEnvironment().getPropertySources().addLast(new CustomPropertySources("crap"));
		}
	    
	}
	
}

class CustomPropertySources extends PropertySource
{
	public CustomPropertySources(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getProperty(String name) {
		
		if (name.equals("dum"))
		{
			return "bla";
		}
		System.out.println("Checking on " + name);
		return null;
	}
}
	