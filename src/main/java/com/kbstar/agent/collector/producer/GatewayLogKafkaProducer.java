package com.kbstar.agent.collector.producer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kbstar.agent.collector.dto.GatewayLogDTO;

// Consumer 테스트 위해 작성(추후 삭제)
@Service
public class GatewayLogKafkaProducer {

	@Autowired
	private KafkaTemplate<String, GatewayLogDTO> kafkaTemplate;

	// Topic 정의 필요
	public void send(GatewayLogDTO gatewayLogDTO) {
		kafkaTemplate.send("gateway-log-topic", gatewayLogDTO);
		
		//키를 지정하는 경우 (메시지를 특정 파티션에 지정시키기 위한 키 지정 여부)
	    //kafkaTemplate.send("gateway-log-topic", "Key1", gatewayLogDTO);
	}

	// 테스트로 작성함. 
	@Scheduled(fixedRate = 1000) 
	public void sendSampleData() {
		GatewayLogDTO gatewayLogDTO = createSampleGatewayLogDTO();
		System.out.println("========================================>\n");
		System.out.println("Send GatewayLogDTO: " + gatewayLogDTO.toString());
		send(gatewayLogDTO);
	
	}

	private GatewayLogDTO createSampleGatewayLogDTO() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String currentDateTime = LocalDateTime.now().format(formatter);

		GatewayLogDTO dto = new GatewayLogDTO();

		dto.setDate(currentDateTime);
		dto.setLogLevel("WARN");
		dto.setTraceStep("TRACEEND");
		dto.setTraceStatus("NORMAL");
		dto.setTraceId(generateUUID());
		dto.setSpanId(generateUUID());
		dto.setParentSpanId("0");
		dto.setRequestTime(currentDateTime);
	
		dto.setResponseTime(responseDateTime());
		
		//시간계산 필요
		dto.setElapsedTime(619057);
		dto.setOrgCode("sample-orgcode-1");
		dto.setOrgNm("sample-orgNm-1");
		dto.setGwId(1);
		dto.setGwNm("sample-gwNm-1");
		dto.setHosts(Collections.singletonList("locahost:13002"));
		dto.setApiId("sample-apild-1");
		dto.setApiNm("sample-apiNm-1");
		dto.setApiUrl("/v1/sample/sampleapi1");
		dto.setClientId("b42002e1b017012c36bd987805386f36");
		dto.setClientNm("sample-clentNm-1");
		dto.setHttpStatusCode(200);
		dto.setErorCode("");
		
		return dto;
	}
	
	private String responseDateTime() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		int randomSeconds = ThreadLocalRandom.current().nextInt(0, 10);
		String responseDateTime = LocalDateTime.now().plusSeconds(randomSeconds).format(formatter);
		return responseDateTime;
	}
	
	private String generateUUID() {
	    return UUID.randomUUID().toString();
	}

}
