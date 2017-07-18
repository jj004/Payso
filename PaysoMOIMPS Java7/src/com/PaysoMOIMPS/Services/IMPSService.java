package com.PaysoMOIMPS.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.PaysoMOIMPS.Utils.ChecksumCRC32;
import com.PaysoMOIMPS.Dao.PaysoMOIMPSDao;
import com.PaysoMOIMPS.Model.PaysoMO_IMPS_Req;
import com.PaysoMOIMPS.Model.PaysoMO_IMPS_Response;

@Path("/IMPSService")
public class IMPSService {
	PaysoMO_IMPS_Req request = null;
	PaysoMOIMPSDao impsDao = null;
	PaysoMO_IMPS_Response response = null;
	DateFormat dt = null;
	Date date = null;
	String[] data;
	int hashCode;
	String requestWithChksum;
	String requestWOChksum;
	String resStr;
	private String Code;
	private String InputParameters;
	private String TraceNo;
	private String BeneAccNo;
	private String BeneIFSC;
	private String BeneAccType;
	private String createdDate;

	@GET
	@Path("/initIMPS")
	public Response initIMPS(@QueryParam("msg") String msg) {
		try {
			String dateTime = new SimpleDateFormat("ddMMYYYYHHmmss").format(Calendar.getInstance().getTime());
			String KotakChksumKey = "KMBANK";
			String PaysoMerchantID = "PAYSO";
			String IMPS_URL = "http://203.196.200.42/pggbm/fiservice";
			dt = new SimpleDateFormat("yyyy-MM-dd");
			date = new Date();
			request = new PaysoMO_IMPS_Req();
			data = msg.split("!");

			hashCode = msg.hashCode();
			Code = msg.hashCode() + dt.format(date);
			InputParameters = msg;
			TraceNo = "" + hashCode;
			BeneAccNo = data[0];
			BeneIFSC = data[1];
			BeneAccType = data[2];
			createdDate = dt.format(date);

			request = new PaysoMO_IMPS_Req();
			request.setCode(Code);
			request.setInputParameters(InputParameters);
			request.setTraceNo(TraceNo);
			request.setBeneAccNo(BeneAccNo);
			request.setBeneIFSC(BeneIFSC);
			request.setBeneAccType(BeneAccType);
			request.setCreatedDate(createdDate);

			impsDao = new PaysoMOIMPSDao();
			int res = impsDao.storeIMPSRequest(request);
			if (res > 0) {
				// requestWOChksum=msg+KotakChksumKey;
				requestWOChksum = "6210|" + dateTime + "|" + PaysoMerchantID + "|" + request.getTraceNo() + "|R|"
						+ request.getBeneIFSC() + "|" + request.getBeneAccNo() + "|1|Account Varification|"
						+ request.getBeneAccType() + "|Payso|919999991199||||";
				long chksum = ChecksumCRC32.generateChecksum(requestWOChksum + "|" + KotakChksumKey);
				requestWithChksum = requestWOChksum + "|" + chksum;
				resStr = new HTTPWSClient().sendRequest(IMPS_URL, requestWithChksum);
				System.out.println(resStr);
				if (resStr != null || resStr != "") {
					if (resStr.length() > 1) {
						hashCode = resStr.hashCode();
						String responseStr = resStr.replace('|', '!');
						data = responseStr.split("!");

						response = new PaysoMO_IMPS_Response();
						response.setResCode(hashCode + dt.format(date));
						response.setCode(request.getCode());
						response.setResParameters(resStr);
						response.setTraceNo(data[3]);
						response.setResponseCode(data[4]);
						response.setErrorReason(data[5]);
						response.setBeneName(data[9]);
						response.setAuthzStatus("-");
						response.setTranDate(dt.format(date));
						if (impsDao.storeIMPSResponse(response) <= 0) {
							return Response.status(400).entity("FAILED to store Response : " + resStr).build();
						}
					}
					return Response.status(200).entity("SUCCESS : " + resStr).build();
				} else {
					return Response.status(400).entity("IMPS FAILED : " + resStr).build();
				}
			} else {
				return Response.status(400).entity("FAILED to store Request : " + resStr).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).entity("FAILED with Exception : " + resStr).build();
		}
	}

	@GET
	@Path("/statusIMPS")
	public Response statusIMPS(@QueryParam("msg") String msg) {
		try {
			System.out.println("Msg = " + msg);
			String QBTraceNo = msg;
			String KotakChksumKey = "KMBANK";
			String PaysoMerchantID = "PAYSO";
			String IMPS_URL = "http://203.196.200.42/pggbm/fiservice";
			String dateTime = new SimpleDateFormat("ddMMYYYYHHmmss").format(Calendar.getInstance().getTime());
			dt = new SimpleDateFormat("yyyy-MM-dd");
			date = new Date();
			
			hashCode = msg.hashCode();
			Code = msg.hashCode() + dt.format(date);
			InputParameters = msg;
			TraceNo = "" + hashCode;
			BeneAccNo = "-";
			BeneIFSC = "-";
			BeneAccType = "-";
			createdDate = dt.format(date);
			
			request = new PaysoMO_IMPS_Req();
			request.setCode(Code);
			request.setInputParameters(InputParameters);
			request.setTraceNo(TraceNo);
			request.setBeneAccNo(BeneAccNo);
			request.setBeneIFSC(BeneIFSC);
			request.setBeneAccType(BeneAccType);
			request.setCreatedDate(createdDate);

			impsDao = new PaysoMOIMPSDao();
			System.out.println("Hello Before Status Request");
			int res = impsDao.storeIMPSRequest(request);
			System.out.println("Hello After Status Request : "+res);
			if (res > 0) {
				System.out.println("Brfore Trace No Check");
				if (impsDao.getResponseTraceNo(msg) > 0) {
					System.out.println("After Trace No Check");
					requestWOChksum = "6310|" + dateTime + "|" + PaysoMerchantID + "|" + request.getTraceNo() + "|"
							+ QBTraceNo;
					long chksum = ChecksumCRC32.generateChecksum(requestWOChksum + "|" + KotakChksumKey);
					requestWithChksum = requestWOChksum + "|" + chksum;
					resStr = new HTTPWSClient().sendRequest(IMPS_URL, requestWithChksum);
					System.out.println(resStr);
					if (resStr != null || resStr != "") {
						if (resStr.length() > 1) {
							hashCode = resStr.hashCode();
							String responseStr = resStr.replace('|', '!');
							data = responseStr.split("!");

							response = new PaysoMO_IMPS_Response();
							response.setResCode(hashCode + dt.format(date));
							response.setCode(request.getCode());
							response.setResParameters(resStr);
							response.setTraceNo(data[3]);
							response.setResponseCode(data[8]);
							response.setErrorReason(data[9]);
							response.setBeneName("-");
							response.setAuthzStatus(data[7]);
							response.setTranDate(dt.format(date));

							if (impsDao.storeIMPSResponse(response) <= 0) {
								return Response.status(400).entity("FAILED to store Response : " + resStr).build();
							}
						}
					}
					return Response.status(200).entity("SUCCESS : " + resStr).build();
				} else {
					return Response.status(400).entity("FAILED : Invalid Trace Number : " + resStr).build();
				}
			} else {
				System.out.println("False");
				return Response.status(400).entity("FAILED : Request Not Stored : " + resStr).build();
			}
		} catch (Exception e) {
			return Response.status(400).entity("FAILED with Exception : " + resStr).build();
		}
	}
}