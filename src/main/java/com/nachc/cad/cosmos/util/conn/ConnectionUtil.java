package com.nachc.cad.cosmos.util.conn;

import java.sql.Connection;
import java.sql.DriverManager;

import com.nachc.cad.cosmos.util.auth.AuthParams;

public class ConnectionUtil {

	public static Connection getMysqlConnection() {
		return getMysqlConnection("cosmos");
	}
	
	public static Connection getMysqlConnection(String schema) {
		try {
			String url = AuthParams.getMysqlUrl();
			String uid = AuthParams.getMysqlUid();
			String pwd = AuthParams.getMysqlPwd();
			url = url + schema;
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
		
	}
	
}
