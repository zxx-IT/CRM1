package com.atguigu.ssm.crm.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.atguigu.ssm.crm.utils.ReflectionUtils;

public class PropertyFilter {

	public static Map<String, Object> parseRequestParams2MybatisParams(
			Map<String, Object> params) {
		List<PropertyFilter> filters = parseRequestParmasToFilters(params);
		return parseFiltersToMybatisParams(filters);
	}

	public static List<PropertyFilter> parseRequestParmasToFilters(
			Map<String, Object> params) {
		List<PropertyFilter> filters = new ArrayList<>();
		if (params != null && params.size() > 0) {
			Set<Entry<String,Object>> entrySet = params.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object propertyValue = entry.getValue();
				if (propertyValue == null || propertyValue.toString().trim().equals("")) {
					continue;
				}
				String str1 = StringUtils.substringBefore(key, "_");
				String matchTypeCode = StringUtils.substring(str1, 0, str1.length() - 1);
				MatchType matchType = Enum.valueOf(MatchType.class, matchTypeCode);
				String propertyTypeCode = StringUtils.substring(str1, str1.length() - 1);
				Class propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getPropertyType();
				
				//createDate
				String propertyName = StringUtils.substringAfter(key, "_");
				
				PropertyFilter filter = new PropertyFilter(propertyName, propertyValue, matchType, propertyType);
				filters.add(filter);
			}
		}
		return filters;
	}

	public static Map<String, Object> parseFiltersToMybatisParams(
			List<PropertyFilter> filters) {
		Map<String, Object> params = new HashMap<String, Object>();
		//1. 遍历 filters
		for(PropertyFilter filter: filters){
			//2. 把 propertyVal 转为 propertyType 对应的 propertyVal. 
			Class propertyType = filter.getPropertyType();
			Object propertyVal = filter.getPropertyVal();
			propertyVal = ReflectionUtils.convertValue(propertyVal, propertyType);
			
			//3. 若 matchType 为 LIKE, 则在 propertyVal 前后加 %
			MatchType matchType = filter.getMatchType();
			if(matchType.equals(MatchType.LIKE)){
				propertyVal = "%" + propertyVal + "%";
			}
			
			String propertyName = filter.getPropertyName();
			params.put(propertyName, propertyVal);
		}
		
		return params;
	}

	private String propertyName;
	private Object propertyVal;
	private MatchType matchType;
	private Class propertyType;

	public PropertyFilter(String propertyName, Object propertyVal,
			MatchType matchType, Class propertyType) {
		this.propertyName = propertyName;
		this.propertyVal = propertyVal;
		this.matchType = matchType;
		this.propertyType = propertyType;
	}
	
	
	public String getPropertyName() {
		return propertyName;
	}

	public Object getPropertyVal() {
		return propertyVal;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public Class getPropertyType() {
		return propertyType;
	}


	public enum MatchType {
		LIKE, GT, LT, GE, LE, EQ;
	}

	public enum PropertyType {
		I(Integer.class), D(Date.class), L(Long.class), S(String.class), F(
				Float.class);

		private Class propertyType;

		public Class getPropertyType() {
			return propertyType;
		}

		private PropertyType(Class propertyType) {
			this.propertyType = propertyType;
		}
	}

	@Override
	public String toString() {
		return "PropertyFilter [propertyName=" + propertyName
				+ ", propertyVal=" + propertyVal + ", matchType=" + matchType
				+ ", propertyType=" + propertyType + "]";
	}

}
