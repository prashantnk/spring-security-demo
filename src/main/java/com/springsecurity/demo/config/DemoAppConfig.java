package com.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.springsecurity.demo" })
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig implements WebMvcConfigurer {

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Autowired
	private Environment env;

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		WebMvcConfigurer.super.addResourceHandlers(registry);

		registry.addResourceHandler("/public/**").addResourceLocations("/resources/");

	}

	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

//		don't give the wrong credentials !! 

		myLogger.info("-----------> env jdbc.driver =  " + env.getProperty("jdbc.driver"));
		myLogger.info("-----------> env jdbc.url =  " + env.getProperty("jdbc.url"));
		myLogger.info("-----------> env jdbc.user =  " + env.getProperty("jdbc.user"));
		myLogger.info("-----------> env jdbc.password =  " + env.getProperty("jdbc.password"));
		myLogger.info("-----------> env connection.pool.initialPoolSize =  "
				+ env.getProperty("connection.pool.initialPoolSize"));
		myLogger.info(
				"-----------> env connection.pool.minPoolSize =  " + env.getProperty("connection.pool.minPoolSize"));
		myLogger.info(
				"-----------> env connection.pool.maxPoolSize =  " + env.getProperty("connection.pool.maxPoolSize"));
		myLogger.info("-----------> env connection.pool.connection.pool.maxIdleTime =  "
				+ env.getProperty("connection.pool.maxIdleTime"));

		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		securityDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		securityDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		securityDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		securityDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));

		return securityDataSource;
	}

}
