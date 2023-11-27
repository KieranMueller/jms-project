package com.kieran.p2p;

import com.kieran.p2p.model.Passenger;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CheckInApp {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext ic = new InitialContext();
        Queue requestQueue = (Queue) ic.lookup("queue/requestQueue");
        Queue replyQueue = (Queue) ic.lookup("queue/replyQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            JMSProducer producer = jmsContext.createProducer();
            Passenger passenger = new Passenger(
                    1L, "Jasper", "Kazuma", "jippers@gmail.com", "671-822-9021");

            producer.setJMSReplyTo(replyQueue);

            producer.send(requestQueue, passenger);

            JMSConsumer replyConsumer = jmsContext.createConsumer(replyQueue);
            MapMessage replyMessage = (MapMessage) replyConsumer.receive();
            System.out.println("Passenger reservation: " +
                    (replyMessage.getBoolean("processed") ? "processed" : "incomplete") +
                    ". Confirmation ID is " + replyMessage.getLong(passenger.getId().toString()));
        }
    }
}
