package com.nachc.cad.cosmos.upload.excel.basic;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.sql.Connection;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.util.conn.ConnectionUtil;
import com.nachc.cad.cosmos.util.proxy.DataSetProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadPatientDataIntegrationTest {

	@Test
	public void shouldUplaodData() {
		uploadFiles();
	}

	public static void uploadFiles() {
		String fileName;
		log.info("\n\n-----------------------------------------------------------\nStarting Upload...");
		fileName = "/files/excel/no-phi-denver-health-patient-only-2020-08-09-DUP.xlsx";
		log.info(fileName);
		doUpload(fileName);
		log.info("\n\n-----------------------------------------------------------\nStarting Upload...");
		fileName = "/files/excel/no-phi-denver-health-patient-only-2020-08-09.xlsx";
		log.info(fileName);
		doUpload(fileName);
	}
	
	private static void doUpload(String fileName) {
		log.info("Starting test...");
		log.info("Getting file");
		InputStream in = FileUtil.getInputStream(fileName);
		String sheetName = "Table 1 - Demographics";
		log.info("Got inputstream: " + in);
		assertTrue(in != null);
		log.info("Getting connection");
		Connection conn = ConnectionUtil.getMysqlConnection();
		log.info("Getting data set");
		DataSetDvo dataSetDvo = DataSetProxy.createTestDataSet(conn);
		log.info("Starting upload");
		new ExcelUploadPatientData().uploadPatientData(in, sheetName, dataSetDvo, conn);
		log.info("Done.");
	}

}
