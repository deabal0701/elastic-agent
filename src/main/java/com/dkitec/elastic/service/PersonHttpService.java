package com.dkitec.elastic.service;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkitec.elastic.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonHttpService {

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	public IndexResponse insertHttpPerson(Person person) throws IOException {
		// 1. Person 객체를 JSON 형식으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		String personJson = objectMapper.writeValueAsString(person);

		// 2. IndexRequest 객체 생성 및 설정
		IndexRequest indexRequest = new IndexRequest("person");
		indexRequest.id(person.getId());
		//indexRequest.source(personJson, XContentType.JSON);

		return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
	}

	public SearchResponse findPersonByName(String name) throws IOException {
		// 검색 쿼리 작성
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("name", name));

		// 검색 요청 작성 및 설정
		SearchRequest searchRequest = new SearchRequest("person");
		searchRequest.source(searchSourceBuilder);

		// 검색 요청 실행
		return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
	}
}
