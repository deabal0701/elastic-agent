package com.kbstar.agent.search.bulk.repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BulkRepository {

    @Autowired
    private RestHighLevelClient client;

    public void bulkInsert(String indexName, List<Map<String, Object>> dataList) {
        BulkRequest bulkRequest = new BulkRequest();

        for (Map<String, Object> data : dataList) {
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.source(data);
            bulkRequest.add(indexRequest);
        }

        try {
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
