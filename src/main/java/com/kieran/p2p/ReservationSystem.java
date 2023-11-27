package com.kieran.p2p;

import com.kieran.p2p.listener.CheckInListener;
import com.kieran.p2p.model.Passenger;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ReservationSystem {

    public static void main(String[] args) throws NamingException, InterruptedException {

        InitialContext ic = new InitialContext();
        Queue requestQueue = (Queue) ic.lookup("queue/requestQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            JMSConsumer consumer = jmsContext.createConsumer(requestQueue);
            consumer.setMessageListener(new CheckInListener());

            Thread.sleep(20_000);
        }
    }

    public void createReservation(Passenger passenger) {
        // do reservation stuff
        System.out.println("Reservation for " + passenger.getFirstName() + " " + passenger.getLastName() + " successful");
    }
}
