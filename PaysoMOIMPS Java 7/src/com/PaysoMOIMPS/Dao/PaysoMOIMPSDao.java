package com.PaysoMOIMPS.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.PaysoMOIMPS.Model.PaysoMO_IMPS_Req;
import com.PaysoMOIMPS.Model.PaysoMO_IMPS_Response;

public class PaysoMOIMPSDao {
	Connection con = null;

	public int storeIMPSRequest(PaysoMO_IMPS_Req request) throws SQLException {
		try {
			System.out.println("Inside Request Store Dao");
			String query = "insert into PaysoMO_IMPS_Req values('" + request.getCode() + "','"
					+ request.getInputParameters() + "','" + request.getTraceNo() + "','" + request.getBeneAccNo() + "','"
					+ request.getBeneIFSC() + "','" + request.getBeneAccType() + "','" + request.getCreatedDate()
					+ "')";
			System.out.println("Query:\n"+query);
			con = DBConnection.getConnection();
			Statement st = con.createStatement();
			int rs = st.executeUpdate(query);
			if (rs > 0) {
				System.out.println("Inside Request Store Dao : Success");
				return 1;
			} else {
				System.out.println("Inside Request Store Dao : Filed");
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			con.close();
		}
	}

	public int storeIMPSResponse(PaysoMO_IMPS_Response res) throws SQLException {
		try {
			String query = "insert into PaysoMO_IMPS_Response values('" + res.getResCode() + "','" + res.getCode()
					+ "','" + res.getResParameters() + "','" + res.getTraceNo() + "','" + res.getResponseCode() + "','"
					+ res.getErrorReason() + "','" + res.getBeneName() + "','" + res.getAuthzStatus() + "','"
					+ res.getTranDate() + "')";
			con = DBConnection.getConnection();
			Statement st = con.createStatement();
			int rs = st.executeUpdate(query);
			if (rs > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			con.close();
		}
	}

	public int getResponseTraceNo(String TraceNo) throws SQLException {
		try {
			System.out.println("Inside Get Trace No");
			String query = "select TraceNo from PaysoMO_IMPS_Response where TraceNo='"+TraceNo+"'";
			System.out.println("Query : "+query);
			con = DBConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				System.out.println("Query Fired Success : "+rs.getString(1));
				return 1; 
			}
			else{
				System.out.println("Query Fired Failed");
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
}