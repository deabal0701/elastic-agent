package com.dkitec.elastic.producer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dkitec.elastic.dto.GatewayLogDTO;

@Service
public class GatewayLogKafkaProducer {

	@Autowired
	private KafkaTemplate<String, GatewayLogDTO> kafkaTemplate;

	public void send(GatewayLogDTO gatewayLogDTO) {
		kafkaTemplate.send("gateway-log-topic", gatewayLogDTO);
	}

	@Scheduled(fixedRate = 1000) // 1 minute interval
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
		dto.setIndex("dummy-gw-log-V2");
		dto.set_id("KMgcuqcBmTfZXbVN903-");
		dto.set_score(1);

		GatewayLogDTO.Source source = new GatewayLogDTO.Source();
		source.setDate(currentDateTime);
		source.setLog_level("WARN");
		source.setTraceStep("TRACEEND");
		source.setTraceStatus("NORMAL");
		source.setTraceId(generateUUID());
		source.setSpanId(generateUUID());
		source.setParentSpanId("0");
		source.setRequestTime(currentDateTime);
	
		source.setResponseTime(responseDateTime());
		
		//시간계산 필요
		source.setElapsedTime(619057);
		source.setOrgcode("sample-orgcode-1");
		source.setOrgNm("sample-orgNm-1");
		source.setGwId(1);
		source.setGwNm("sample-gwNm-1");
		source.setHost(Collections.singletonList("locahost:13002"));
		source.setApiId("sample-apild-1");
		source.setApiNm("sample-apiNm-1");
		source.setApiUrl("/v1/sample/sampleapi1");
		source.setClientId("b42002e1b017012c36bd987805386f36");
		source.setClientNm("sample-clentNm-1");
		source.setHttpStatusCode(200);
		source.setErorCode("");

		dto.set_source(source);
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
