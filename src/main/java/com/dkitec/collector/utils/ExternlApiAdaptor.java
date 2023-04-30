package com.dkitec.collector.utils;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ExternlApiAdaptor {
	
	//@Value("#{'${xxx.system.url}'.split(',')}")
	private List<String> urls;
	//@Value("${xxxx.system.contextPath}")
	private String contextPath;

	//@Value("${xxxx.apikey}")
	//private String apiKey;
	
	private static int roundRobinCount = 1;

	private int getRoundRobinCount(int size) {
		if (roundRobinCount > size) {
			roundRobinCount = 1;
		}
		return roundRobinCount++;
	}

	private URI getUrl(String path) {
		if (urls != null && urls.size() > 0) {
			return getUrl(urls.get(getRoundRobinCount(urls.size()) - 1) + contextPath, path);
		}
		return null;
	}

	protected URI getUrl(String baseUrl, String path) {
		return getUrl(baseUrl, path, null);
	}
	
	protected URI getUrl(String baseUrl, String path, Map<String, String> param) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl).path(path);
		if (param != null) {
			Iterator<String> iteratortor = param.keySet().iterator();
			while (iteratortor.hasNext()) {
				String key = (String) iteratortor.next();
				builder.queryParam(key, param.get(key));
			}
		}
		return builder.build().toUri();
	}

	protected <T> HttpEntity<T> getRequestEntity(T body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.set(Constants.APIKEY, apiKey);
		return new HttpEntity<T>(body, headers);
	}

	private RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(5 * 1000);
		factory.setReadTimeout(10 * 1000);
		RestTemplate restTemplate = new RestTemplate(factory);
		
		//필요한 경우 로그를 남기는 Interceptor add.
		
		return restTemplate;
	}

	public <T> T post(String path, Object body, Class<T> responseType) {
    	try {
    		return getRestTemplate().postForObject(getUrl(path), getRequestEntity(body), responseType);
		} catch (ResourceAccessException e) {
			log.error(e.getMessage(), e);
			try {
				return getRestTemplate().postForObject(getUrl(path), getRequestEntity(body), responseType);
			} catch (ResourceAccessException e1) {
				log.error(e.getMessage(), e);
				return getRestTemplate().postForObject(getUrl(path), getRequestEntity(body), responseType);
			}
		}
	}
	
	public <T> T post(String path, Object body, ParameterizedTypeReference<T> responseType) {
	    try {
	        ResponseEntity<T> responseEntity = getRestTemplate().exchange(getUrl(path), HttpMethod.POST, getRequestEntity(body), responseType);
	        return responseEntity.getBody();
	    } catch (ResourceAccessException e) {
	        log.error(e.getMessage(), e);
	        try {
	            ResponseEntity<T> responseEntity = getRestTemplate().exchange(getUrl(path), HttpMethod.POST, getRequestEntity(body), responseType);
	            return responseEntity.getBody();
	        } catch (ResourceAccessException e1) {
	            log.error(e.getMessage(), e);
	            ResponseEntity<T> responseEntity = getRestTemplate().exchange(getUrl(path), HttpMethod.POST, getRequestEntity(body), responseType);
	            return responseEntity.getBody();
	        }
	    }
	}

    public <T> T get(String path, Class<T> responseType) {
        try {
            return getRestTemplate().exchange(getUrl(path), HttpMethod.GET, getRequestEntity(null), responseType).getBody();
        } catch (ResourceAccessException e) {
            log.error(e.getMessage(), e);
            try {
                return getRestTemplate().exchange(getUrl(path), HttpMethod.GET, getRequestEntity(null), responseType).getBody();
            } catch (ResourceAccessException e1) {
                log.error(e.getMessage(), e);
                return getRestTemplate().exchange(getUrl(path), HttpMethod.GET, getRequestEntity(null), responseType).getBody();
            }
        }
    }
}