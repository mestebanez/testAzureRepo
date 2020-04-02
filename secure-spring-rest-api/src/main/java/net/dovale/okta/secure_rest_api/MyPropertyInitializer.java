package net.dovale.okta.secure_rest_api;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class MyPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
    	CustomProperty1Sources ps = new CustomProperty1Sources("dum");
        applicationContext.getEnvironment().getPropertySources().addFirst(ps);
    }

}

class CustomProperty1Sources extends PropertySource
{
	public CustomProperty1Sources(String name) {
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