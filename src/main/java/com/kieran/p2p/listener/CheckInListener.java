package com.kieran.p2p.listener;

import com.kieran.p2p.ReservationSystem;
import com.kieran.p2p.model.Passenger;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

public class CheckInListener implements MessageListener {

    ReservationSystem reservationSystem = new ReservationSystem();

    @Override
    public void onMessage(Message message) {

        ObjectMessage objectMessage = (ObjectMessage) message;

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            Queue replyQueue = (Queue) message.getJMSReplyTo();

            JMSProducer replyProducer = jmsContext.createProducer();

            Passenger passenger = (Passenger) objectMessage.getObject();

            System.out.println("Processing reservation for passenger: " + passenger);
            reservationSystem.createReservation(passenger);

            MapMessage replyMessage = jmsContext.createMapMessage();
            replyMessage.setBoolean("processed", true);
            replyMessage.setLong(passenger.getId().toString(), (long) Math.floor(Math.random() * 999));
            replyProducer.send(replyQueue, replyMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
