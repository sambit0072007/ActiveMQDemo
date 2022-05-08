package com.demo.util;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQUtil {
	
	
	public static  String activeMQConnectionUtility(
			ActiveMQConnectionFactory factory,
			String queueName,String input  )  throws Exception
	{
		
		Connection jmsConnection=factory.createConnection();
		jmsConnection.start();
		 Session session = jmsConnection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		 Destination destination=session.createQueue("STLQUEUE");
		 MessageProducer producer = session.createProducer(destination);
		// Message message = session.createTextMessage("Hello !!! Welcome to the world of ActiveMQ.");
		 System.out.println("Producing Message:: "+input);
		 Message message = session.createTextMessage(input);     
	        // Here we are sending our message!
	        producer.send(message);
		 return "Successfully Done";
	}

}
