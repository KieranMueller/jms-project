package com.kieran.p2p;

import com.kieran.p2p.model.Employee;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HumanResourcesApp {

    public static void main(String[] args) throws NamingException {

        InitialContext ic = new InitialContext();
        Topic topic = (Topic) ic.lookup("topic/employeeTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            Employee employee = new Employee(
                    1L,
                    "Jasper",
                    "San",
                    "Jips@gmail.com",
                    "Software Developer",
                    "712-209-2817");

            jmsContext.createProducer().send(topic, employee);
            System.out.println("Message sent");
        }
    }
}
