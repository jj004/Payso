package com.PaysoMOIMPS.model;

public class PaysoMO_IMPS_Req {
	private String Code;
	private String InputParameters;
	private String TraceNo;
	private String BeneAccNo;
	private String BeneIFSC;
	private String BeneAccType;
	private String createdDate;
	
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getInputParameters() {
		return InputParameters;
	}
	public void setInputParameters(String inputParameters) {
		InputParameters = inputParameters;
	}
	public String getTraceNo() {
		return TraceNo;
	}
	public void setTraceNo(String traceNo) {
		TraceNo = traceNo;
	}
	public String getBeneAccNo() {
		return BeneAccNo;
	}
	public void setBeneAccNo(String beneAccNo) {
		BeneAccNo = beneAccNo;
	}
	public String getBeneIFSC() {
		return BeneIFSC;
	}
	public void setBeneIFSC(String beneIFSC) {
		BeneIFSC = beneIFSC;
	}
	public String getBeneAccType() {
		return BeneAccType;
	}
	public void setBeneAccType(String beneAccType) {
		BeneAccType = beneAccType;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
