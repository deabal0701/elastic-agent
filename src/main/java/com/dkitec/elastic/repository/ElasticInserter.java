package com.dkitec.elastic.repository;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkitec.elastic.entity.GatewayLogDTO;

@Service
public class ElasticInserter {

	@Autowired
	private RestHighLevelClient restHighLevelClient;
	
    private static final String INDEX_NAME = "gateway_logs";

    public void insertGatewayLog(Map<String,Object> log) throws IOException {
        XContentBuilder builder = prepareXContentBuilder(log);
        IndexRequest request = new IndexRequest(INDEX_NAME).source(builder);
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    private XContentBuilder prepareXContentBuilder(GatewayLogDTO log) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();

        builder.startObject();
        {
            builder.field("routeInfo", log.getRouteInfo());
            builder.field("clientInfo", log.getClientInfo());
            builder.field("xKbGatewayCode", log.getXKbGatewayCode());
            builder.field("filterInfoList", log.getFilterInfoList());
            builder.field("apiServer", log.getApiServer());
            builder.field("traceId", log.getTraceId());
            builder.field("spanId", log.getSpanId());
            builder.field("parentSpanId", log.getParentSpanId());
            builder.field("traceStatus", log.getTraceStatus());
            builder.field("traceStep", log.getTraceStep());
            builder.field("httpStatusCode", log.getHttpStatusCode());
            builder.field("elapsedTime", log.getElapsedTime());
            builder.field("path", log.getPath());
            builder.field("apiServerPath", log.getApiServerPath());
            builder.field("gatewayConfig", log.getGatewayConfig());
            builder.field("errorCode", log.getErrorCode());
        }
        builder.endObject();

        return builder;
    }
}

