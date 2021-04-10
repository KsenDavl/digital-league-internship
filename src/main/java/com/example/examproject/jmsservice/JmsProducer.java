package com.example.examproject.jmsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JmsProducer {

    private final JmsTemplate jmsTemplate;

    private Map<String, Integer> methodsCount = new HashMap<>();
    private int getMethods = 0;
    private int postMethods = 0;
    private int putMethods = 0;
    private int deleteMethods = 0;

    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String queueName, HttpMethod httpMethod) {

        switch (httpMethod) {
            case GET:
                getMethods++;
                break;
            case POST:
                postMethods++;
                break;
            case PUT:
                putMethods++;
                break;
            case DELETE:
                deleteMethods++;
        }

        String message = String.format("Во время работы сервера было вызвано: \n %d методов GET, " +
                "%d методов PUT, %d методов POST, %d методов DELETE", getMethods, putMethods, postMethods, deleteMethods);


        try{
            jmsTemplate.convertAndSend(queueName, message);
        } catch(Exception e){
            throw new RuntimeException("Received Exception during send Message: ", e);
        }
    }
}
