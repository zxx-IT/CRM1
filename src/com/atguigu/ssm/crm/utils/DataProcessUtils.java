package com.atguigu.ssm.crm.utils;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class DataProcessUtils {
	public static String encodeParamsToQueryString(Map<String, Object> params) {
		StringBuilder builder = new StringBuilder();
		if (params != null && params.size() > 0) {
			Set<Entry<String, Object>> entrySet = params.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if ("".equals(value)) {
					continue;
				}
				builder.append("search_").append(key).append("=").append(value)
						.append("&");
				
			}
			if (builder.length() > 0) {
				builder.replace(builder.length() - 1, builder.length(), "");
			}
		}
		return builder.toString();
	}
}
