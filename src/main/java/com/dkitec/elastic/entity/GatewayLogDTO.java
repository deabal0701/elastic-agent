package com.dkitec.elastic.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize
@JsonDeserialize
public class GatewayLogDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RouteInfo routeInfo;
    private ClientInfo clientInfo;
    private List<String> xKbGatewayCode;
    private List<FilterInfo> filterInfoList;
    private ApiServer apiServer;
    private String traceId;
    private String spanId;
    private String parentSpanId;
    private String traceStatus;
    private String traceStep;
    private int httpStatusCode;
    private long elapsedTime;
    private String path;
    private String apiServerPath;
    private GatewayConfig gatewayConfig;
    private String errorCode;

    @Data
    @JsonSerialize
    @JsonDeserialize
    public static class RouteInfo {
        private String routeId;
        private LocalDateTime requestTime;
        private LocalDateTime responseTime;
    }

    @Data
    @JsonSerialize
    @JsonDeserialize
    public static class ClientInfo {
        private String uuId;
        private String logPreFix;
        private String clientId;
        private String requestBody;
        private String httpMethod;
        private Map<String, List<String>> requestHeader;
        private List<String> xFtotTraceid;
        private String xKbApiServerPath;
    }

    @Data
    @JsonSerialize
    @JsonDeserialize
    public static class FilterInfo {
        private String filterName;
        private LocalDateTime completeTime;
        private long elapsedTime;
    }
    
    @Data
    @JsonSerialize
    @JsonDeserialize
    public static class ApiServer {
        private String responselogPreFix;
        private String responseStatus;
        private LocalDateTime apiServerReponseTime;
        private String responseBody;
        private int responseCode;
        private Map<String, List<String>> responseHeader;

    }

    @Data
    @JsonSerialize
    @JsonDeserialize
    public static class GatewayConfig {
        private int sid;
        private boolean loggingYN;
        private boolean loggingBodyYN;
        private boolean external;
        private boolean validationkbGuid;
        private boolean ganeratekBGuid;
        private boolean bodySaveSkip;
        private List<String> authBasicApiIds;

    }
}
