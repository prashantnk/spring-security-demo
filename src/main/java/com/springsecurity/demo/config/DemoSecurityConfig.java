package com.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		@SuppressWarnings("deprecation")
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication().withUser(users.username("john").password("test").roles("Employee", "Manager"))
//				.withUser(users.username("mary").password("test").roles("Admin"))
//				.withUser(users.username("susan").password("test").roles("Employee", "Admin"));

		auth.jdbcAuthentication().dataSource(securityDataSource);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/employees/**").hasAnyRole("Employee")
				.antMatchers("/leaders/**").hasRole("Manager").antMatchers("/systems/**").hasRole("Admin").anyRequest()
				.authenticated().and().formLogin().loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateTheUser").permitAll().and().logout().logoutSuccessUrl("/").permitAll()
				.and().exceptionHandling().accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		super.configure(web);
		web.ignoring().antMatchers("/public/**");
	}

}
