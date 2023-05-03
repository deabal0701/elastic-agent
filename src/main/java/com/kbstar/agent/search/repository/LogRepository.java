package com.kbstar.agent.search.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kbstar.agent.collector.dto.GatewayLogDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class LogRepository {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper = new ObjectMapper();
    
    public List<GatewayLogDTO> findLogs(String traceId, String dateFrom, String dateTo) {
        SearchRequest searchRequest = new SearchRequest("gateway_logs");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (traceId != null && !traceId.isEmpty()) {
            boolQueryBuilder.must(QueryBuilders.termQuery("traceId", traceId));
        }

        if (dateFrom != null && !dateFrom.isEmpty() && dateTo != null && !dateTo.isEmpty()) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("date").from(dateFrom).to(dateTo));
        }

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        List<GatewayLogDTO> logs = new ArrayList<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : searchResponse.getHits().getHits()) {
                GatewayLogDTO log = objectMapper.readValue(hit.getSourceAsString(), GatewayLogDTO.class);
                logs.add(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logs;
    }
}
