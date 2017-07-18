package com.PaysoMOIMPS.model;

public class PaysoMO_IMPS_Response {
	private String ResCode;
	private String Code;
	private String ResParameters;
	private String TraceNo;
	private String ResponseCode;
	private String ErrorReason;
	private String BeneName;
	private String AuthzStatus;
	private String TranDate;
	
	public String getResCode() {
		return ResCode;
	}
	public void setResCode(String resCode) {
		ResCode = resCode;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getResParameters() {
		return ResParameters;
	}
	public void setResParameters(String resParameters) {
		ResParameters = resParameters;
	}
	public String getTraceNo() {
		return TraceNo;
	}
	public void setTraceNo(String traceNo) {
		TraceNo = traceNo;
	}
	public String getResponseCode() {
		return ResponseCode;
	}
	public void setResponseCode(String responseCode) {
		ResponseCode = responseCode;
	}
	public String getErrorReason() {
		return ErrorReason;
	}
	public void setErrorReason(String errorReason) {
		ErrorReason = errorReason;
	}
	public String getBeneName() {
		return BeneName;
	}
	public void setBeneName(String beneName) {
		BeneName = beneName;
	}
	public String getAuthzStatus() {
		return AuthzStatus;
	}
	public void setAuthzStatus(String authzStatus) {
		AuthzStatus = authzStatus;
	}
	public String getTranDate() {
		return TranDate;
	}
	public void setTranDate(String tranDate) {
		TranDate = tranDate;
	}
}