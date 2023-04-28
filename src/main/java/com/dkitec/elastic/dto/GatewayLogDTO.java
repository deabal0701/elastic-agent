package com.dkitec.elastic.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GatewayLogDTO {

    private String index;
    private String _id;
    private int _score;
    private Source _source;

    @Data
    @ToString
    public static class Source {
        private String date;
        private String log_level;
        private String traceStep;
        private String traceStatus;
        private String traceId;
        private String spanId;
        private String parentSpanId;
        private String requestTime;
        private String responseTime;
        private long elapsedTime;
        private String orgcode;
        private String orgNm;
        private int gwId;
        private String gwNm;
        private List<String> host;
        private String apiId;
        private String apiNm;
        private String apiUrl;
        private String clientId;
        private String clientNm;
        private int httpStatusCode;
        private String erorCode;
    }
}
