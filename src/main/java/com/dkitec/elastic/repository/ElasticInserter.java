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

    private XContentBuilder prepareXContentBuilder(Map<String, Object> log) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();

        builder.startObject();
        {
            builder.field("routeInfo", log.get("routeInfo"));
            builder.field("clientInfo", log.get("clientInfo"));
            builder.field("xKbGatewayCode", log.get("xKbGatewayCode"));
            builder.field("filterInfoList", log.get("filterInfoList"));
            builder.field("apiServer", log.get("apiServer"));
            builder.field("traceId", log.get("traceId"));
            builder.field("spanId", log.get("spanId"));
            builder.field("parentSpanId", log.get("parentSpanId"));
            builder.field("traceStatus", log.get("traceStatus"));
            builder.field("traceStep", log.get("traceStep"));
            builder.field("httpStatusCode", log.get("httpStatusCode"));
            builder.field("elapsedTime", log.get("elapsedTime"));
            builder.field("path", log.get("path"));
            builder.field("apiServerPath", log.get("apiServerPath"));
            builder.field("gatewayConfig", log.get("gatewayConfig"));
            builder.field("errorCode", log.get("errorCode"));
        }
        builder.endObject();

        return builder;
    }
    
}

