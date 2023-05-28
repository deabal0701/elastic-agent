package com.kbstar.agent.search.bulk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbstar.agent.search.bulk.repository.BulkRepository;

@Service
public class BulkService {

    @Autowired
    private BulkRepository bulkRepository;

    public void bulkInsert(String indexName, List<Map<String, Object>> dataList) {
    	bulkRepository.bulkInsert(indexName, dataList);
    }
}


