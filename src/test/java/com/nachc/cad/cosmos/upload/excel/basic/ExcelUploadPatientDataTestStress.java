package com.nachc.cad.cosmos.upload.excel.basic;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.sql.Connection;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.nach.core.util.excel.ExcelUtil;
import com.nach.core.util.file.FileUtil;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.util.conn.ConnectionUtil;
import com.nachc.cad.cosmos.util.proxy.DataSetProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadPatientDataTestStress {

	@Test
	public void shouldUplaodData() {
		uploadFiles();
	}

	public static void uploadFiles() {
		String fileName;
		/*
		 * log.
		 * info("\n\n-----------------------------------------------------------\nStarting Upload..."
		 * ); fileName =
		 * "/files/excel/no-phi-denver-health-patient-only-2020-08-09-DUP.xlsx";
		 * log.info(fileName); doUpload(fileName);
		 */
		int max = 500;
		for (int i = 0; i < max; i++) {
			log.info("\n\n-----------------------------------------------------------\nFILE " + (i + 1) + " of " + max + " Starting Upload...");
			fileName = "/files/excel/no-phi-denver-health-patient-only-2020-08-09.xlsx";
			log.info(fileName);
			doUpload(fileName);
		}
	}

	private static void doUpload(String fileName) {
		log.info("Starting test...");
		log.info("Getting file");
		InputStream in = FileUtil.getInputStream(fileName);
		log.info("Got inputstream: " + in);
		assertTrue(in != null);
		log.info("Getting workbook and sheet");
		Workbook book = ExcelUtil.getWorkbook(in);
		Sheet sheet = ExcelUtil.getSheet(book, "Table 1 - Demographics");
		log.info("Getting connection");
		Connection conn = ConnectionUtil.getMysqlConnection();
		log.info("Getting data set");
		DataSetDvo dataSetDvo = DataSetProxy.createTestDataSet(conn);
		log.info("Starting upload");
		ExcelUploadPatientData.uploadPatientData(sheet, dataSetDvo, conn);
		log.info("Done.");
	}

}
