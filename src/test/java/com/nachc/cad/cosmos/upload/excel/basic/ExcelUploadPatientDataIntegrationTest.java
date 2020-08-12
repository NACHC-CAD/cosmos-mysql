package com.nachc.cad.cosmos.upload.excel.basic;

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
		log.info("Starting test...");
		log.info("Getting file");
		InputStream in = FileUtil.getInputStream("/files/excel/no-phi-denver-health-patient-only-2020-08-9.xlsx");
		String sheetName = "Table 1 - Demographics";
		log.info("Got inputstream: " + in);
		log.info("Getting connection");
		Connection conn = ConnectionUtil.getMysqlConnection();
		log.info("Getting data set");
		DataSetDvo dataSetDvo = DataSetProxy.createTestDataSet(conn);
		log.info("Starting upload");
		new ExcelUploadPatientData().uploadPatientData(in, sheetName, dataSetDvo, conn);
		log.info("Done.");
	}
	
}
