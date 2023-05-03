package com.kbstar.agent.collector.service;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbstar.agent.collector.dto.GatewayLogDTO;
import com.kbstar.agent.collector.repository.ElasticInserterRepository;

@Service
public class ElasticInserterService {

	@Autowired
	ElasticInserterRepository elasticInserterRepository;
	
	 @Autowired
	 DataHandler dataHandler;
	    

	private static final String INDEX_NAME = "gateway_logs";

	public void insertGatewayLog(GatewayLogDTO log) {

		try {
			XContentBuilder builder = prepareXContentBuilder(log);
			IndexRequest request = new IndexRequest(INDEX_NAME).source(builder);
			elasticInserterRepository.ElasticInserter(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private XContentBuilder prepareXContentBuilder(GatewayLogDTO source) throws IOException {
		XContentBuilder builder = XContentFactory.jsonBuilder();

		builder.startObject();
		{
			builder.field("date", source.getDate());
			builder.field("log_level", source.getLogLevel());
			builder.field("traceStep", source.getTraceStep());
			builder.field("traceStatus", source.getTraceStatus());
			builder.field("traceId", source.getTraceId());
			builder.field("spanId", source.getSpanId());
			builder.field("parentSpanId", source.getParentSpanId());
			builder.field("requestTime", source.getRequestTime());
			builder.field("responseTime", source.getResponseTime());
			builder.field("elapsedTime", source.getElapsedTime());
			builder.field("orgcode", source.getOrgCode());
		//	builder.field("orgNm", source.getOrgNm());
			builder.field("orgNm", dataHandler.getOrgName(source.getOrgCode()));
			builder.field("gwId", source.getGwId());
		//	builder.field("gwNm", source.getGwNm());
			builder.field("gwNm", dataHandler.getGwName(source.getGwId()));
			builder.field("hosts", source.getHosts());
			builder.field("apiId", source.getApiId());
		//	builder.field("apiNm", source.getApiNm());
			builder.field("apiNm", dataHandler.getApiName(source.getApiId()));
			builder.field("apiUrl", source.getApiUrl());
			builder.field("clientId", source.getClientId());
		//	builder.field("clientNm", source.getClientNm());
			builder.field("clientNm", dataHandler.getClientName(source.getClientId()));
			builder.field("httpStatusCode", source.getHttpStatusCode());
			builder.field("erorCode", source.getErorCode());
		}
		builder.endObject();

		return builder;
	}
}
