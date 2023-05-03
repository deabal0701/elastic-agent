package com.dkitec.collector.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.dkitec.collector.dto.GatewayLogDTO;
import com.dkitec.collector.exception.GatewayLogDTOException;
import com.dkitec.collector.service.ElasticInserterService;

@Service
public class GatewayLogKafkaConsumer {
	
	@Autowired
	ElasticInserterService elasticInserterService;

    @KafkaListener(topics = "gateway-log-topic", groupId = "gateway-log-group", errorHandler = "gatewayLogErrorHandler")
    public void listen(ConsumerRecord<String, GatewayLogDTO> record, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            GatewayLogDTO gatewayLogDTO = record.value();
            if (gatewayLogDTO == null) {
            	// Gateway Log가 비정상적일때.. 전달받은 값이라도 처리 할지 여부에 대해서는 판단필요
            	// GatewayLogErrDTO class를 생성하여 error메시지를 담아서 elastic서치로 저장 추후
                throw new GatewayLogDTOException("Error occurred in GatewayLog");
            }
            
            //Elastic 서치가 종료되어있을때 처리방안(offset조정등 방안)
            elasticInserterService.insertGatewayLog(gatewayLogDTO);
            System.out.println("<=========================================[received]");
            System.out.println("Received GatewayLogDTO: " + gatewayLogDTO.toString());
            System.out.println("Partition: " + partition);
            System.out.println("Offset: " + offset);
            
        } catch (GatewayLogDTOException e) {
            System.err.println("Error occurred while processing GatewayLogDTO: " + e.getMessage());
        }
    }
}