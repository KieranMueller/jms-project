package com.kieran.p2p;

import com.kieran.p2p.model.Transaction;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CreditCardApp {

    public static void main(String[] args) throws NamingException {

        InitialContext ic = new InitialContext();
        Topic topic = (Topic) ic.lookup("topic/creditTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            Transaction transaction = new Transaction(
                    "Jasper L. Shinto",
                    "671-294-9901",
                    "Holiday Gas Co.",
                    "2804 Lake St, Minneapolis, MN 55403",
                    244.07);

            jmsContext.createProducer().send(topic, transaction);
            System.out.println("Transaction sent");
        }
    }
}
