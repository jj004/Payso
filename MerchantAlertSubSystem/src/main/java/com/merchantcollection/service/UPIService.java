package com.merchantcollection.service;

import java.util.Map;

import org.json.simple.JSONObject;

public interface UPIService {

	void storeRequest(Map<String,String> kotakMap, JSONObject requestJson);
}