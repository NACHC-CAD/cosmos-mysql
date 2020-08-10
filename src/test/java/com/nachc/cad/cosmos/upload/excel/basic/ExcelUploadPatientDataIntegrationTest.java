package com.nachc.cad.cosmos.upload.excel.basic;

import java.io.InputStream;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

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
		new ExcelUploadPatientData().uploadPatientData(in, sheetName);
		log.info("Done.");
	}
	
}
