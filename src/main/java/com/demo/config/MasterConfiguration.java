package com.demo.config;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*Front Controller */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.*"})
public class MasterConfiguration implements WebMvcConfigurer {
	
	
	public static final String URL="jdbc:mysql://localhost:3306/springmvc";
	public static final String USERNAME="root";
	public static final String PASSWORD="root";
	public static final String DRIVER_CLASS_NAME="com.mysql.cj.jdbc.Driver";
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		System.out.println("Setting up InternalResourceViewResolver ");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DriverManagerDataSource driverManagerDataSource() throws Exception {
		
		DriverManagerDataSource datasource= new DriverManagerDataSource();
		datasource.setDriverClassName(DRIVER_CLASS_NAME);
		datasource.setUrl(URL);
		datasource.setUsername(USERNAME);
		datasource.setPassword(PASSWORD);
		System.out.println("Database connection established with MySql instance: ");
		datasource.getConnection().getTypeMap().entrySet().stream().forEach(x->System.out.println(x.getValue()));
	 return datasource;
	}
	
	
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean sessionFactoryBean= new LocalSessionFactoryBean();
		//sessionFactoryBean.setAnnotatedClasses(null);
		Properties properties= new Properties();
		//properties.setProperty(PASSWORD, DRIVER_CLASS_NAME)
		//sessionFactoryBean.setHibernateProperties(null);
		return sessionFactoryBean;
		
	}
	
	@Bean 
	public ActiveMQConnectionFactory activeMQConnectionFactory() throws JMSException {
		ActiveMQConnectionFactory activeMQConnectionFactory= new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setUserName("admin");
		activeMQConnectionFactory.setPassword("admin");
		activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
		Connection connection= activeMQConnectionFactory.createConnection();
		
		System.out.println(connection.getMetaData().getJMSVersion());
		
		
		
		return activeMQConnectionFactory;
		
	}
	
	

}
