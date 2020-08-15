package com.integration.pivottable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.yaorma.database.Database;

import com.nach.core.util.random.RandomUtil;
import com.nachc.cad.cosmos.util.conn.ConnectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulatePivotTableExampleDatabase {

	public static void main(String[] args) throws Exception {
		log.info("Starting data upload...");
		log.info("Getting connection");
		Connection conn = ConnectionUtil.getMysqlConnection("examples");
		doUpload(conn);
		log.info("Done.");
	}

	public static void doUpload(Connection conn) {
		log.info("Deleting existing data...");
		Database.update("truncate table attribute", conn);
		log.info("Doing inserts");
		String sqlString = "insert into attribute values (null,?,?,?)";
		int max = 1000000;
		PreparedStatement ps = Database.getPreparedStatement(sqlString, conn);
		for (int i = 0; i < max; i++) {
			// add params
			String subjectId = i+"";
			addParam(subjectId, "GENDER", getGender(), ps);
			addParam(subjectId, "AGE", getAge(), ps);
			addParam(subjectId, "CITY", getCity(), ps);
			addParam(subjectId, "FAVORITE_COLOR", getColor(), ps);
			addParam(subjectId, "PET", getPet(), ps);
			if (i % 1000 == 0) {
				log.info("Executing " + i + " of " + max);
				Database.execute(ps);
				log.info("Done with batch update");
				ps = Database.getPreparedStatement(sqlString, conn);
			}
		}
		if (Database.isClosed(ps) == false) {
			Database.execute(ps);
		}
	}

	private static void addParam(String subjectId, String name, String val, PreparedStatement ps) {
		ArrayList<String> params;
		params = new ArrayList<String>();
		params.add(subjectId + "");
		params.add(name);
		params.add(val);
		Database.addToBatch(params, ps);
	}

	private static String getCity() {
		String[] list = { "Hartford", "Boston", "New York", "East Lyme" };
		return RandomUtil.getRandom(list);
	}

	private static String getAge() {
		return RandomUtil.getRandom(100) + "";
	}

	private static String getGender() {
		String[] list = {"M", "F"};
		return RandomUtil.getRandom(list);
	}

	private static String getColor() {
		String[] list = {"Red","Orange","Yellow","Green","Blue","Indigo","Violet"};
		return RandomUtil.getRandom(list);
		
	}
	
	private static String getPet() {
		String[] list = {"Dog","Cat","Bird","Fish","Turtle","Elephant","Zebra","Tiger"};
		return RandomUtil.getRandom(list);
	}
	
}
