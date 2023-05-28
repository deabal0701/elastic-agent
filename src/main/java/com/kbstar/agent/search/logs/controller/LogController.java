package com.kbstar.agent.search.logs.controller;

import java.util.List;

import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kbstar.agent.collector.dto.GatewayLogDTO;
import com.kbstar.agent.search.logs.service.LogService;


@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    public ResponseEntity<List<GatewayLogDTO>> getLogs(@RequestParam(required = false) String traceId,
                                                        @RequestParam(required = false) String dateFrom,
                                                        @RequestParam(required = false) String dateTo) {
        List<GatewayLogDTO> logs = logService.findLogs(traceId, dateFrom, dateTo);
        return ResponseEntity.ok(logs);
    }
    
    @GetMapping("/searchhit")
    public ResponseEntity<List<SearchHit>> getSearchHit(@RequestParam(required = false) String traceId,
                                                        @RequestParam(required = false) String dateFrom,
                                                        @RequestParam(required = false) String dateTo) {
        List<SearchHit> logs = logService.searchHit(traceId, dateFrom, dateTo);
        return ResponseEntity.ok(logs);
    }
}