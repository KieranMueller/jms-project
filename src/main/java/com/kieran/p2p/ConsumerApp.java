package com.kieran.p2p;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConsumerApp {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext ic = new InitialContext();
        Queue requestQueue = (Queue) ic.lookup("queue/requestQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            JMSConsumer consumer = jmsContext.createConsumer(requestQueue);
//            TextMessage message = (TextMessage) consumer.receive();
//            System.out.println(message.getText());
            for (int i = 0; i < 10; i++) {
                System.out.println(consumer.receiveBody(String.class));
            }
        }
    }
}
