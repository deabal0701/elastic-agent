package com.dkitec.elastic.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Service
public class MyKafkaConsumer {

	@KafkaListener(topics = "my-topic", groupId = "my-group")
	public void listen(ConsumerRecord<String, String> record, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
			@Header(KafkaHeaders.OFFSET) long offset) {
		System.out.println("Received Message: " + record.value());
		System.out.println("Partition: " + partition);
		System.out.println("Offset: " + offset);
	}
}