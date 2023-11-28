package com.kieran.p2p;

import com.kieran.p2p.model.Transaction;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Timestamp;

public class SecurityApp {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext ic = new InitialContext();
        Topic topic = (Topic) ic.lookup("topic/creditTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            jmsContext.setClientID("SecurityApp");

            JMSConsumer consumer = jmsContext.createDurableConsumer(topic, "consumer1");

            Message message = consumer.receive();

            Transaction transaction = message.getBody(Transaction.class);

            System.out.println("Starting security check for " + transaction.getCardHolder());

            securityCheck(
                    transaction.getPhoneNumber(),
                    transaction.getAmount(),
                    transaction.getPaidTo(),
                    transaction.getLocation(),
                    transaction.getTime()
            );
        }
    }

    private static void securityCheck(String phoneNumber, Double amount, String paidTo, String location, Timestamp time) {

        // do security stuff, check typical behavior in DB
        if (amount > 100) {
            // flagAccount()
            System.out.println("Sending SMS to " + phoneNumber);
            System.out.println("SMS Body: ### Unusual behavior has been detected on your account ###");
            System.out.println("At " + time + ", an amount of $" + amount + " was paid to " + paidTo + " at " + location);
            System.out.println("Placing temporary hold on account, please contact support: 1-800-242-9088");
        } else {
            // do nothing
        }
    }
}
