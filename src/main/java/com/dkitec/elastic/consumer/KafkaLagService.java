package com.dkitec.elastic.consumer;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsOptions;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaLagService {

	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	@Autowired
	private ConsumerFactory<String, String> consumerFactory;

	@Autowired
	private AdminClient adminClient;

	@Scheduled(fixedRate = 6000000) // 1분마다 실행
	public void printLagForConsumerGroup() {
		try {
			Set<String> topicSet = adminClient.listTopics().names().get();
			ConsumerGroupDescription consumerGroupDescription = adminClient
					.describeConsumerGroups(Collections.singleton(groupId)).describedGroups().get(groupId).get();

			Map<TopicPartition, OffsetAndMetadata> groupOffsets = adminClient
					.listConsumerGroupOffsets(groupId, new ListConsumerGroupOffsetsOptions())
					.partitionsToOffsetAndMetadata().get();

			try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(
					consumerFactory.getConfigurationProperties())) {
				for (TopicPartition topicPartition : groupOffsets.keySet()) {
					if (topicSet.contains(topicPartition.topic())) {
						kafkaConsumer.assign(Collections.singleton(topicPartition));
						kafkaConsumer.seekToEnd(Collections.singleton(topicPartition));
						long endOffset = kafkaConsumer.position(topicPartition);
						long currentOffset = groupOffsets.get(topicPartition).offset();
						long lag = endOffset - currentOffset;

						System.out.printf("Topic: %s, Partition: %d, End Offset: %d, Current Offset: %d, Lag: %d%n",
								topicPartition.topic(), topicPartition.partition(), endOffset, currentOffset, lag);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
