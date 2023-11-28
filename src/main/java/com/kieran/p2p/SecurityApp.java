package com.kieran.p2p;

import com.kieran.p2p.model.Employee;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SecurityApp {

    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {

        InitialContext ic = new InitialContext();
        Topic topic = (Topic) ic.lookup("topic/employeeTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            jmsContext.setClientID("SecurityApp");

            JMSConsumer consumerToGoDown = jmsContext.createDurableConsumer(topic, "subscriber1");
            consumerToGoDown.close();

            Thread.sleep(10_000);

            JMSConsumer consumer = jmsContext.createDurableConsumer(topic, "subscriber1");
            Message message = consumer.receive();
            Employee employee = message.getBody(Employee.class);

            System.out.println("Security application processing new employee: "
                    + employee.getFirstName() + " " + employee.getLastName());
        }
    }
}
