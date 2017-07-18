package com.PaysoMOIMPS.Services;

import java.net.MalformedURLException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class HTTPWSClient {
	String res = null;
	public String sendRequest(String httpUrl, String requestStr) throws MalformedURLException {
		try {
			PostMethod pm = new PostMethod(httpUrl);
			pm.addParameter("msg", requestStr);
			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);
			res = pm.getResponseBodyAsString();
			//res=pm.getResponseBody();
			//res=pm.getResponseBodyAsStream();
			//res=pm.getResponseCharSet();
		} catch (Exception e) {
		} finally {
		}
		return res;
	}
}