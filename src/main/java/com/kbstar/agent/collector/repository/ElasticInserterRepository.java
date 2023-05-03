package com.kbstar.agent.collector.repository;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElasticInserterRepository {
	
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	
	// 엘라스틱 서치에 데이터 저장 
	public void ElasticInserter(IndexRequest request) throws IOException {
	    restHighLevelClient.index(request, RequestOptions.DEFAULT);
		
	}
}
