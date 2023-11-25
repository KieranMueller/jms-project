package com.kieran.p2p;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.kieran.p2p.model.Patient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ClinicalsApp {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext ic = new InitialContext();
        Queue requestQueue = (Queue) ic.lookup("queue/requestQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            JMSProducer producer = jmsContext.createProducer();
            ObjectMessage objectMessage = jmsContext.createObjectMessage();
            Patient patient = new Patient(
                    1L, "Jasper", "Blue Cross", 5D, 25D);
            objectMessage.setObject(patient);
            producer.send(requestQueue, objectMessage);
        }
    }
}
