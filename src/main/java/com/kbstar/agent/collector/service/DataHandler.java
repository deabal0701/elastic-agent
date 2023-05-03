package com.kbstar.agent.collector.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kbstar.agent.collector.LogCollectorApplication;

@Service
public class DataHandler {

	private static DataHandler instance;

	@Autowired
	private ConfigurableApplicationContext context;

	private final Map<String, String> orgData = Collections.synchronizedMap(new HashMap<>());
	private final Map<Integer, String> gwData = Collections.synchronizedMap(new HashMap<>());
	private final Map<String, String> apiData = Collections.synchronizedMap(new HashMap<>());
	private final Map<String, String> clientData = Collections.synchronizedMap(new HashMap<>());

	private ExternalDataService externalApi;

	@PostConstruct
	public void init() {
		// 포털 명칭 데이터 로드
		loadDataFromExternalSource();
		System.out.println("### 최초 실행 ### ");

		// 명칭데이터 로드 실패시 로그와 함게 종료
		//LogCollectorApplication.exit(context, this, 1);

	}

	@Autowired
	public DataHandler(ExternalDataService externalApi) {
		this.externalApi = externalApi;
		instance = this;
	}

	public static DataHandler getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Scheduled(fixedRateString = "${datahandler.load.interval.millis}")
	public void loadDataFromExternalSource() {

		Map<String, Map<String, Object>> data = externalApi.fetchData();

		orgData.putAll((Map<String, String>) (Map) data.get("orgData"));
		gwData.putAll((Map<Integer, String>) (Map) data.get("gwData"));
		apiData.putAll((Map<String, String>) (Map) data.get("apiData"));
		clientData.putAll((Map<String, String>) (Map) data.get("clientData"));
	}

	// org 데이터 조회
	public String getOrgName(String orgCode) {
		return orgData.get(orgCode);
	}

	// gw 데이터 조회
	public String getGwName(Integer gwId) {
		return gwData.get(gwId);
	}

	// API 데이터 조회
	public String getApiName(String apiId) {
		return apiData.get(apiId);
	}

	// 클라이언트 데이터 조회
	public String getClientName(String clientId) {
		return clientData.get(clientId);
	}
}
