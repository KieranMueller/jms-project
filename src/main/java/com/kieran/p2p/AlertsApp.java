package com.kieran.p2p;

import com.kieran.p2p.model.Transaction;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Timestamp;

public class AlertsApp {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext ic = new InitialContext();
        Topic topic = (Topic) ic.lookup("topic/creditTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            jmsContext.setClientID("AlertsApp");

            JMSConsumer consumer = jmsContext.createDurableConsumer(topic, "consumer1");

            Message message = consumer.receive();

            Transaction transaction = message.getBody(Transaction.class);

            System.out.println("Processing Alert For " + transaction.getCardHolder());

            sendAlert(
                    transaction.getPhoneNumber(),
                    transaction.getLocation(),
                    transaction.getPaidTo(),
                    transaction.getTime(),
                    transaction.getAmount()
            );
        }
    }

    private static void sendAlert(String phoneNumber, String location, String paidTo, Timestamp time, Double amount) {
        System.out.println("Sent Alert To: " + phoneNumber);
        System.out.println("SMS Message Body: " + "### At " + time + ", you paid $"
                + amount + " to " + paidTo + " at location: " + location + " ###");
        System.out.println("If this was not you, please contact support: 1-800-242-9088");
    }
}
