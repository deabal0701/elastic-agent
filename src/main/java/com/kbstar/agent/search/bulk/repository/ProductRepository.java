package com.kbstar.agent.search.bulk.repository;

import java.io.IOException;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final RestHighLevelClient client;

    public ProductRepository(RestHighLevelClient client) {
        this.client = client;
    }

//    public Terms getSumPriceByProduct() {
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.matchAllQuery());
//        builder.aggregation(AggregationBuilders.sum("total_price").field("price"));
//
//        SearchRequest request = new SearchRequest("bulk_test");
//        request.source(builder);
//
//        try {
//        	SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        	Aggregations aggregations = response.getAggregations();
//        	Terms terms = aggregations.get("total_price");
//        	return terms;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
 //   }
    
    public Sum getSumPriceByProduct() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.aggregation(AggregationBuilders.sum("total_price").field("price"));

        SearchRequest request = new SearchRequest("bulk_test");
        request.source(builder);

        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            Sum sum = aggregations.get("total_price");
            return sum;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  




}



