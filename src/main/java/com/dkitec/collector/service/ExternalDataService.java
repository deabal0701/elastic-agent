package com.dkitec.collector.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkitec.collector.utils.ExternlApiAdaptor;

@Service
public class ExternalDataService {
	
	@Autowired
	ExternlApiAdaptor externlApiAdaptor;
	
	
    public Map<String, Map<String, Object>> fetchData() {
        
    	// 명칭데이터를 가져온다. (규격에 따라 변경 필요)
        //Map<String,Map<String, Object>> data =  externlApiAdaptor.get("/url", Map.class);
        
    	
        // 위의 코드 완료되면 아래 코드 삭제 
    	Map<String,Map<String, Object>> data = new HashMap<>();
        Map<String, Object> orgData = new HashMap<>();
        Map<String, Object> gwData = new HashMap<>();
        Map<String, Object> apiData = new HashMap<>();
        Map<String, Object> clientData = new HashMap<>();

   
        for (int i = 1; i <= 100; i++) {
            orgData.put("orgCode" + i, "orgName" + i);
            gwData.put("gwId" + i, "gwName" + i);
            apiData.put("apiId" + i, "apiName" + i);
            clientData.put("clientId" + i, "clientName" + i);
        }

        data.put("orgData", orgData);
        data.put("gwData", gwData);
        data.put("apiData", apiData);
        data.put("clientData", clientData);

        return data;
    }
}

