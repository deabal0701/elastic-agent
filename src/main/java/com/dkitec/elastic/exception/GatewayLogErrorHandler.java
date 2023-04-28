package com.dkitec.elastic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class GatewayLogErrorHandler implements KafkaListenerErrorHandler, ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayLogErrorHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
        LOGGER.error("Failed to process gateway log message: {}", message.getHeaders().get(KafkaHeaders.RECEIVED_MESSAGE_KEY), exception);
        // Implement your custom logic here, for example, retry or stop the container, etc.
        return null;
    }

    @Override
    public void handle(Exception thrownException, org.apache.kafka.clients.consumer.ConsumerRecord<?, ?> data) {
        LOGGER.error("Error processing gateway log record: {}", data, thrownException);
        // Implement your custom logic here, for example, retry or stop the container, etc.
    }
}