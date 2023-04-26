package com.dkitec.elastic.repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.dkitec.elastic.entity.GatewayLogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class InsertGatewayLog {

    public static void insertGatewayLog(String json) throws IOException {
        GatewayLogDTO log = parseJsonToGatewayLogDTO(json);

        
        // 시간 관련 필드 설정
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime requestTime = now;
        log.getRouteInfo().setRequestTime(requestTime);

        int randomResponseTimeSeconds = new Random().nextInt(8) + 3; // 랜덤 3-10초
        LocalDateTime responseTime = now.plusSeconds(randomResponseTimeSeconds);
        log.getRouteInfo().setResponseTime(responseTime);

        log.getClientInfo().getRequestHeader().put("X-KB-Client-Request-Date", List.of(now.toLocalDate().toString()));
        log.getApiServer().setApiServerReponseTime(responseTime);

        // 시간이 들어가는 나머지 항목
      //  log.getFilterInfoList().forEach(filterInfo -> {
      //      filterInfo.setCompleteTime(requestTime.plusSeconds(1));
      //  });

        ElasticInserter inserter = new ElasticInserter();
        
        String StrJson = convertGatewayLogDTOToJson(log);
        Map<String, Object> logMap = convertJsonToMap(StrJson);
        inserter.insertGatewayLog(logMap);
    }

    private static GatewayLogDTO parseJsonToGatewayLogDTO(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        
        // LocalDateTimeDeserializer 재정의
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        objectMapper.registerModule(module);
        
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        
        return objectMapper.readValue(json, GatewayLogDTO.class);
    }
    
    private static String convertGatewayLogDTOToJson(GatewayLogDTO log) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(log);
    }
    
    private static Map<String, Object> convertJsonToMap(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
    }


}
