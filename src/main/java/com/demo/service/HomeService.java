package com.demo.service;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.util.ActiveMQUtil;

@Service
public class HomeService {
	{
		System.out.println("Inside HomeService");
	}
	
	@Autowired
	ActiveMQConnectionFactory  factory;
	
	
	
	
	
	
	public String postMessage(String message) throws Exception {
		//Connection jmsConnection=factory.createConnection();
		//jmsConnection.start();
		 //Session session = jmsConnection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		 //Destination destination=session.createQueue("STLQUEUE");
		 //MessageProducer producer = session.createProducer(destination);
		String operation= ActiveMQUtil.activeMQConnectionUtility(factory, "STLQUEUE",message );
		
		return "message --> "+operation;
	}

	public String publishMessage() throws Exception {
		Connection jmsConnection=factory.createConnection();
		jmsConnection.start();
		 Session session = jmsConnection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		 Destination destination=session.createQueue("STLQUEUE");
		 MessageConsumer consumer = session.createConsumer(destination);
	     Message message = consumer.receive();
	    
	     if (message instanceof TextMessage) {
	            TextMessage textMessage = (TextMessage) message;
	            System.out.println("Received message '" + textMessage.getText() + "'");
	        }
	    jmsConnection.close(); 
		return "Home Details from HomeService"+message.toString();
	}
}
