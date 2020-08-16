package com.nachc.cad.cosmos.upload.excel.basic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.yaorma.dao.Dao;

import com.nach.core.util.excel.ExcelUtil;
import com.nach.core.util.file.FileUtil;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.util.conn.ConnectionUtil;
import com.nachc.cad.cosmos.util.proxy.DataSetProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadEncounterIntegrationTest {

	private static final String WORKBOOK = "/files/excel/no-phi-denver-health-2020-08-9-TRUNC.xlsx";
	
	@Test
	public void shouldLoadEncounterData() {
		log.info("Starting test...");
		log.info("Getting connection");
		Connection conn = ConnectionUtil.getMysqlConnection();
		log.info("Getting workbook...");
		InputStream in = FileUtil.getInputStream(WORKBOOK);
		Workbook book = ExcelUtil.getWorkbook(in);
		Sheet sheet = book.getSheet("Table 2 - Encounters");
		log.info("DATA SET UPLOAD...");
		DataSetDvo dataSetDvo = DataSetProxy.createTestDataSet(conn);
		log.info("PATIENT UPLOAD...");
		/*
		ExcelUploadPatientData.uploadPatientData(patientSheet, "Table 1 - Demographics", dataSetDvo, conn);
		log.info("ENCOUNTER UPLOAD...");
		ExcelUploadEncounters.uploadEncounters(sheet, dataSetDvo, conn);
		*/
		log.info("Done.");
	}
	
	
}
