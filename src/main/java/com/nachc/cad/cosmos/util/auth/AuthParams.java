package com.nachc.cad.cosmos.util.auth;

import java.util.Properties;

import com.nach.core.util.props.PropertiesUtil;

public class AuthParams {

	private static final String PARAMS_FILE = "/auth/auth.properties";

	private static Properties props;

	static {
		try {
			props = PropertiesUtil.getAsProperties(PARAMS_FILE);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	// ---
	//
	// get property by name
	//
	// ---
	
	public static String getProperty(String key) {
		String rtn = props.getProperty(key);
		if (rtn != null) {
			rtn = rtn.trim();
		}
		return rtn;
	}

	// ---
	//
	// methods to get specific properties
	//
	// ---
	
	public static String getMysqlUrl() {
		return getProperty("mysql-url");
	}
	
	public static String getMysqlUid() {
		return getProperty("mysql-uid");
	}
	
	public static String getMysqlPwd() {
		return getProperty("mysql-pwd");
	}
	
}
