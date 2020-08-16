package com.nachc.cad.cosmos.util.proxy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import com.nachc.cad.cosmos.dvo.PatientAttTypeDvo;

public class PatientAttTypeProxy {

	public static PatientAttTypeDvo findForProjectAndCode(String projectId, String code, Connection conn) {
		code = code.toLowerCase();
		ArrayList<String> params = new ArrayList<String>();
		params.add(projectId);
		params.add(code);
		String sqlString = "";
		sqlString += "select * from patient_att_type \n";
		sqlString += "where project_id = ? and lower(code) = ? \n";
		ResultSet rs = null;
		try {
			PatientAttTypeDvo rtn = null;
			rs = Database.executeQuery(sqlString, params, conn);
			if (rs.next()) {
				rtn = new PatientAttTypeDvo();
				Dao.load(rtn, rs);
			}
			return rtn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.closeResultSet(rs);
		}
	}

}
