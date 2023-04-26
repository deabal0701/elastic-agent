package com.dkitec.elastic.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dkitec.elastic.repository.InsertGatewayLog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ElastcInsertTestController {

	@Autowired
	InsertGatewayLog insertGatewayLog;

	@GetMapping("execute")
	public String greeting(@RequestParam(value = "count", defaultValue = "1") Integer count) {
		
		  String json = "{\n"
	        		+ "  \"routeInfo\": {\n"
	        		+ "    \"routeId\": \"1\",\n"
	        		+ "    \"requestTime\": \"2023-03-31T10:28:56.043582800\",\n"
	        		+ "    \"responseTime\": \"2023-03-31T10:39:15.093690800\"\n"
	        		+ "  },\n"
	        		+ "  \"clientInfo\": {\n"
	        		+ "    \"uuId\": \"[df7c2ce -e155-498f-ad01-48c4ed43bb77, 592d4db0-89d8-44bb-bb0d-9ff11caf872f]\",\n"
	        		+ "    \"logPreFix\": \"[ea4720c0-1, L:/[0:0:0:0:0:0:0:1]:13002R:/[0:0:0:0:0:0:0:1]: 57751] \",\n"
	        		+ "    \"clientId\": \"b42002e1b017012c36bd987805386f36\",\n"
	        		+ "    \"requestBody\": \"Z3JhbnRfdHlWZT1jbG1 1bnRfY3}1ZGVudGIhbHM=\",\n"
	        		+ "    \"httpMethod\": \"POST\",\n"
	        		+ "    \"requestHeader\": {\n"
	        		+ "      \"Authorization\": [\n"
	        		+ "        \"Basic I1ppenNLa3pmU25qY3doVGlLUA==\"\n"
	        		+ "      ],\n"
	        		+ "      \"X-KB-Client-Request-Date\": [\n"
	        		+ "        \"20230327\"\n"
	        		+ "      ],\n"
	        		+ "      \"User-Agent \": [\n"
	        		+ "        \"PostmanRuntime/7.29.2\"\n"
	        		+ "      ],\n"
	        		+ "      \"Accept\": [\n"
	        		+ "        \"**\"\n"
	        		+ "      ],\n"
	        		+ "      \"Postman-Token\": [\n"
	        		+ "        \"e8a370ee-5053-4b46-9019-bba94c88ffc5\"\n"
	        		+ "      ],\n"
	        		+ "      \"Host\": [\n"
	        		+ "        \"localhost: 13002\"\n"
	        		+ "      ],\n"
	        		+ "      \"Accept-Encoding\": [\n"
	        		+ "        \"gzip, deflate, br\"\n"
	        		+ "      ],\n"
	        		+ "      \"Connection\": [\n"
	        		+ "        \"keep-alive\"\n"
	        		+ "      ],\n"
	        		+ "      \"Content-Type\": [\n"
	        		+ "        \"application/x-www-form-urlencoded\"\n"
	        		+ "      ],\n"
	        		+ "      \"Content-Length\": [\n"
	        		+ "        \"29\"\n"
	        		+ "      ],\n"
	        		+ "      \"X-GW-UUID\": [\n"
	        		+ "        \"8df7c2ce-e155-498f-ad01-48c4ed43bb77\"\n"
	        		+ "      ],\n"
	        		+ "      \"X-KB-GATEWAY-PATH\": [\"/v1/oauth/token\"]\n"
	        		+ "    },\n"
	        		+ "    \"x-ftot-traceid\": [\n"
	        		+ "      \"831f749d-295e-4c66-8969-3cac0a9a5948\",\n"
	        		+ "      \"592d4db0-89d8-44bb-bbØd-9ff11caf872f\"\n"
	        		+ "    ],\n"
	        		+ "    \"X-KB-API-SERVER-PATH\": \"/oauth/token\"\n"
	        		+ "  },\n"
	        		+ "  \"X-KB-GATEWAY-CODE\": [\n"
	        		+ "    \"EXT\"\n"
	        		+ "  ],\n"
	        		+ "  \" filterInfolist\": [\n"
	        		+ "    {\n"
	        		+ "      \"filterName\": \"GatewayContextFilter\",\n"
	        		+ "      \"completeTime\": \"2023-03-31T10:28:56.059583900\",\n"
	        		+ "      \"elapsedTime\": 22\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"filterName\": \"CommonHeaderGlobalFilter\",\n"
	        		+ "      \"completeTime\": \"2023-03-31T10:28:56.184149500\",\n"
	        		+ "      \"elapsedTime\": 41\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"filterName\": \"AuthorizationGlobalFilter\",\n"
	        		+ "      \"completeTime\": \"2023-03-31T10:28:56.253675500\",\n"
	        		+ "      \"elapsedTime\": 28\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"filterName\": \"ClientCredentialFilter\",\n"
	        		+ "      \"completeTime\": \"2023-03-31T10:28:56.291690100\",\n"
	        		+ "      \"elapsedTime\": 0\n"
	        		+ "    }\n"
	        		+ "  ],\n"
	        		+ "  \"apiServer\": {\n"
	        		+ "    \"responselogPreFix\": \"[ea4720c0-1, L:/[0:0:0:0:0+0:0:1]:13002 - R:/[0:0:0:0:0:0:0:1]:57751] \",\n"
	        		+ "    \"responseStatus\": \"OK\",\n"
	        		+ "    \"apiServerReponseTime\": \"2023-03-31T10:39:15.049188\",\n"
	        		+ "    \"responseBody\": \"<E,0>1MDRiZWYifQ==\",\n"
	        		+ "    \"responseCode\": 200,\n"
	        		+ "    \"responseHeader\": {\n"
	        		+ "      \"Cache-Control\": [\n"
	        		+ "        \"no-store\"\n"
	        		+ "      ],\n"
	        		+ "      \"Pragma \": [\n"
	        		+ "        \"no-cache\"\n"
	        		+ "      ],\n"
	        		+ "      \"X-Content -Type-Options\": [\n"
	        		+ "        \"nosniff\"\n"
	        		+ "      ],\n"
	        		+ "      \"X-XSS-Protection\": [\n"
	        		+ "        \"1; mode-block\"\n"
	        		+ "      ],\n"
	        		+ "      \"X-Frame-Options\": [\n"
	        		+ "        \"DENY\"\n"
	        		+ "      ],\n"
	        		+ "      \"Content-Type\": [\n"
	        		+ "        \"application/json; charset=UTF-8\"\n"
	        		+ "      ],\n"
	        		+ "      \"Date\": [\n"
	        		+ "        \"Fri, 31 Mar 2023 01:39:14 GMT\"\n"
	        		+ "      ],\n"
	        		+ "      \"Content-Length\": [\n"
	        		+ "        \"412\"\n"
	        		+ "      ]\n"
	        		+ "    }\n"
	        		+ "  },\n"
	        		+ "  \"traceId\": \"831f749d-295e-4c66-8969-3cac0a9a5948\",\n"
	        		+ "  \"spanId\": \"592d4db0-89d8-44bb-bbØd-9ff11caf872f\",\n"
	        		+ "  \" parentSpanId\": \"0\",\n"
	        		+ "  \"traceStatus\": \"NORMAL\",\n"
	        		+ "  \"traceStep\": \"TRACEEND\",\n"
	        		+ "  \"httpStatusCode\": 200,\n"
	        		+ "  \"elapsedTime\": 619057,\n"
	        		+ "  \"path\": \"/v1/oauth/token\",\n"
	        		+ "  \"apiServerPath\": \"/oauth/token\",\n"
	        		+ "  \"gatewayConfig\": {\n"
	        		+ "    \"sid\": 1,\n"
	        		+ "    \"loggingyN\": true,\n"
	        		+ "    \"loggingBodyyN\": true,\n"
	        		+ "    \"external\": true,\n"
	        		+ "    \"validationkbGuid\": false,\n"
	        		+ "    \"ganeratekBGuid\": false,\n"
	        		+ "    \"bodySaveSkip\": false,\n"
	        		+ "    \"authBasicApiIds\": [\n"
	        		+ "      \"1\",\n"
	        		+ "      \"193\"\n"
	        		+ "    ]\n"
	        		+ "  },\n"
	        		+ "  \"errorCode\": \"\"\n"
	        		+ "}";

	        int numberOfExecutionsPerSecond = 1; // 실행 횟수 설정 (예: 1)
	        int numberOfSecondsToRun = count;    // 실행할 시간 설정 (초 단위)

	        log.info(json);
	        long startTime = System.currentTimeMillis();

	        for (int i = 0; i < numberOfExecutionsPerSecond * numberOfSecondsToRun; i++) {
	            try {
	            	insertGatewayLog.insertGatewayLog(json);
	                log.info("{} 행이 인서트 되었습니다.", Integer.valueOf(i + 1));

	                // 다음 작업을 수행하기 전에 일정 시간 대기
	                long elapsedTime = System.currentTimeMillis() - startTime;
	                long waitTime = 1000 / numberOfExecutionsPerSecond - elapsedTime % 1000;
	                if (waitTime > 0) {
	                    Thread.sleep(waitTime);
	                }
	            } catch (IOException | InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		
		return "Complete, " + count + "!";
	}
}
