package com.kbstar.agent.collector.exception;

import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GatewayLogErrorHandler implements KafkaListenerErrorHandler, ErrorHandler {

   
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
    	log.error("Failed to process gateway log message: {}", message.getHeaders().get(KafkaHeaders.RECEIVED_MESSAGE_KEY), exception);
        // 재시도, 종료 등 custom 필요한 로직 구현 
      
        return null;
    }

    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> data) {
    	log.error("Error processing gateway log record: {}", data, thrownException);
    	 // 재시도, 종료 등 custom 필요한 로직 구현 
    }
}