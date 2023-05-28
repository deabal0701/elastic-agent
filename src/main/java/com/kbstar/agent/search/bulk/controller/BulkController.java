package com.kbstar.agent.search.bulk.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbstar.agent.search.bulk.service.BulkService;

@RestController
@RequestMapping("/api/bulk")
public class BulkController {

    @Autowired
    private BulkService bulkService;

    @PostMapping("/insert")
    public ResponseEntity<String> bulkInsert(@RequestBody List<Map<String, Object>> dataList) {
        String indexName ="bulk_test";
        bulkService.bulkInsert(indexName, dataList);

        return ResponseEntity.ok("Data inserted successfully.");
    }
}
