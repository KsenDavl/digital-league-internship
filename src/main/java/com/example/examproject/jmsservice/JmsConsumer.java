package com.example.examproject.jmsservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {

    @Override
    @JmsListener(destination = "exam.topic")
    public void onMessage(Message message) {
        try {
            String text = ((ActiveMQTextMessage)message).getText();
            log.info("Received message : " + text);
        } catch (JMSException e) {
            throw new RuntimeException("");
        }
    }
}
