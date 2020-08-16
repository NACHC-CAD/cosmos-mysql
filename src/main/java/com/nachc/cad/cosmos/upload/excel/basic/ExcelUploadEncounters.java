package com.nachc.cad.cosmos.upload.excel.basic;

import java.sql.Connection;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.yaorma.dao.Dao;

import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.dvo.PatientDvo;

import ca.uhn.fhir.rest.annotation.GetPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadEncounters {

	public static void uploadEncounters(Sheet sheet, DataSetDvo dataSetDvo, Connection conn) {
		log.info("Getting Encounters.");
		log.info("Getting patients");
		getPatientsForDataSet(dataSetDvo, conn);
		log.info("Done with uploadEncounters");
	}

	private static List<PatientDvo> getPatientsForDataSet(DataSetDvo dataSetDvo, Connection conn) {
		String dataSetId = dataSetDvo.getId();
		String sqlString = "select * from patient where data_set_id = ?";
		List<PatientDvo> rtn = Dao.findList(new PatientDvo(), sqlString, dataSetId, conn);
		return rtn;
	}

}
