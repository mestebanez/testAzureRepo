/*package net.dovale.okta.secure_rest_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off

    	 http.authorizeRequests().anyRequest().authenticated()
         .and().oauth2Client()
         .and().oauth2Login();

         //http.csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository()).and().
           //     authorizeRequests()
             //   .antMatchers("/**").permitAll()   ;
        // @formatter:on
    }
}
*/