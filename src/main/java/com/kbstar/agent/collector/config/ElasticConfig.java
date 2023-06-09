package com.kbstar.agent.collector.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {

	  	@Value("${spring.elasticsearch.uris}")
	    private String elasticsearchUrl;

	    @Bean
	    public RestHighLevelClient client() {
	        HttpHost httpHost = HttpHost.create(elasticsearchUrl);
	        return new RestHighLevelClient(RestClient.builder(httpHost));
	    }
}


