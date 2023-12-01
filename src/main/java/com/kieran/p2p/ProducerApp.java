package com.kieran.p2p;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class ProducerApp {

    public static void main(String[] args) throws NamingException {

        InitialContext ic = new InitialContext();
        Queue requestQueue = (Queue) ic.lookup("queue/requestQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext(JMSContext.SESSION_TRANSACTED)) {

            JMSProducer producer = jmsContext.createProducer();

            producer.send(requestQueue, "Message 1");
            producer.send(requestQueue, "Message 2");
            producer.send(requestQueue, "Message 3");

            var scanner = new Scanner(System.in);
            System.out.println("Enter (1) to commit, (2) to rollback");
            int selection = scanner.nextInt();

            switch (selection) {
                case 1 -> jmsContext.commit();
                case 2 -> jmsContext.rollback();
                default -> System.out.println("invalid selection");
            }
            scanner.close();
        }
    }
}
