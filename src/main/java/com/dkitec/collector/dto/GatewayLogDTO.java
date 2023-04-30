package com.dkitec.collector.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GatewayLogDTO {

	// 아래 명칭은 Camel Case 및 문맥에 맞게 변경하였으니 참고할것.
	private String date;
	private String logLevel;
	private String traceStep;
	private String traceStatus;
	private String traceId;
	private String spanId;
	private String parentSpanId;
	private String requestTime;
	private String responseTime;
	private long elapsedTime;
	private String orgCode;
	private String orgNm;
	private int gwId;
	private String gwNm;
	private List<String> hosts;
	private String apiId;
	private String apiNm;
	private String apiUrl;
	private String clientId;
	private String clientNm;
	private int httpStatusCode;
	private String erorCode;

}
