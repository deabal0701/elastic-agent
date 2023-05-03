package com.kbstar.agent.collector.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GatewayLogDTODeserializer implements Deserializer<GatewayLogDTO> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public GatewayLogDTO deserialize(String topic, byte[] data) {
        if (data == null) {
        	//데이터가 null이 발생한 경우(처리방안)
            return null;
        }

        try {
            return objectMapper.readValue(data, GatewayLogDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize GatewayLogDTO", e);
        }
    }

    @Override
    public void close() {
    }
}
