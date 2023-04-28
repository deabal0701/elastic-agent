package com.dkitec.elastic.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.dkitec.elastic.dto.GatewayLogDTO;
import com.dkitec.elastic.exception.GatewayLogDTOException;

@Service
public class GatewayLogKafkaConsumer {

    @KafkaListener(topics = "gateway-log-topic", groupId = "gateway-log-group", errorHandler = "gatewayLogErrorHandler")
    public void listen(ConsumerRecord<String, GatewayLogDTO> record, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            GatewayLogDTO gatewayLogDTO = record.value();
            if (gatewayLogDTO == null || gatewayLogDTO.get_source() == null) {
                throw new GatewayLogDTOException("Invalid GatewayLogDTO format.");
            }
            System.out.println("<========================================");
            System.out.println("Received GatewayLogDTO: " + gatewayLogDTO.toString());
            System.out.println("Partition: " + partition);
            System.out.println("Offset: " + offset);
            
        } catch (GatewayLogDTOException e) {
            System.err.println("Error occurred while processing GatewayLogDTO: " + e.getMessage());
        }
    }
}