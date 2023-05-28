package com.kbstar.agent.search.logs.service;

import java.util.List;

import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbstar.agent.collector.dto.GatewayLogDTO;
import com.kbstar.agent.search.logs.repository.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public List<GatewayLogDTO> findLogs(String traceId, String dateFrom, String dateTo) {
        return logRepository.findLogs(traceId, dateFrom, dateTo);
    }
    
    public List<SearchHit> searchHit(String traceId, String dateFrom, String dateTo) {
        return logRepository.searchHit(traceId, dateFrom, dateTo);
    }
}
