package com.kieran.p2p;

import com.kieran.p2p.model.Employee;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WellnessApp {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext ic = new InitialContext();
        Topic topic = (Topic) ic.lookup("topic/employeeTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            JMSConsumer consumer = jmsContext.createConsumer(topic);
            Message message = consumer.receive();
            Employee employee = message.getBody(Employee.class);

            System.out.println("Wellness application processing new employee: "
                    + employee.getFirstName() + " " + employee.getLastName());
        }
    }
}
