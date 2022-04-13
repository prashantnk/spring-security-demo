package com.springsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springsecurity.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private DataSource securityDataSource;

	@Autowired
	private UserService userService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		return bpe;
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider ap = new DaoAuthenticationProvider();
		ap.setUserDetailsService(userService);
		ap.setPasswordEncoder(passwordEncoder());
		return ap;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		@SuppressWarnings("deprecation")
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication().withUser(users.username("john").password("test").roles("Employee", "Manager"))
//				.withUser(users.username("mary").password("test").roles("Admin"))
//				.withUser(users.username("susan").password("test").roles("Employee", "Admin"));

//		default authentication provided by spring security

//		auth.jdbcAuthentication().dataSource(securityDataSource);

//		custom authentication

		auth.authenticationProvider(authProvider());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()

//		show registerform
				.antMatchers("/register/**").permitAll()

				.antMatchers("/employees/**").hasAnyRole("Employee", "EMPLOYEE").antMatchers("/leaders/**")
				.hasAnyRole("Manager", "MANAGER").antMatchers("/systems/**").hasAnyRole("Admin", "ADMIN").anyRequest()
				.authenticated().and()

//				login handler

				.formLogin().loginPage("/showLoginPage").loginProcessingUrl("/authenticateTheUser")
//				add the handler on successful authentication
				.permitAll().and().logout().logoutSuccessUrl("/")
//				on logout 
				.permitAll().and().exceptionHandling().accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		super.configure(web);
		web.ignoring().antMatchers("/public/**");
	}

}
