package com.nagarro.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StringUtils {
	public StringUtils() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, String> parseQueryString(String url) {
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build();
		MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();

		Map<String, String> keyValueMap = new HashMap<String, String>();
		for (String key : queryParams.keySet()) {
			List<String> values = queryParams.get(key);
			if (!values.isEmpty()) {
				keyValueMap.put(key, values.get(0));
			}
		}
		return keyValueMap;
	}
}
