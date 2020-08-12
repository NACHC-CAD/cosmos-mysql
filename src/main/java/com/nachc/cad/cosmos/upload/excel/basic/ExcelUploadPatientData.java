package com.nachc.cad.cosmos.upload.excel.basic;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.yaorma.dao.Dao;

import com.nach.core.util.excel.ExcelUtil;
import com.nach.core.util.guid.GuidFactory;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.dvo.PatientDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadPatientData {

	/**
	 * 
	 * Uploads a single Excel workbook with the following assumptions
	 * - First col is the patient id
	 * - All other columns are simple properties of the patient
	 * - First row is the names of the parameters
	 * 
	 */	
	public void uploadPatientData(InputStream in, String sheetName, String dataSetId, Connection conn) {
		// get the excel sheet
		log.debug("Getting sheet");
		Sheet sheet = ExcelUtil.getSheet(in, sheetName);
		log.debug("Got sheet: " + sheet.getSheetName());
		// get the parameter names from the first row of the sheet
		log.debug("Getting params");
		ArrayList<String> params = getParameterNames(sheet);
		// iterate through each row of the sheet
		log.debug("Getting rows");
		Iterator<Row> rows = ExcelUtil.getRows(sheet);
		int lastRow = sheet.getLastRowNum();
		log.debug("Uploading rows...");
		while (rows.hasNext()) {
			// get the row
			Row row = rows.next();
			// skip the first row (it's the header row)
			if (row.getRowNum() == 0) {
				continue;
			}
			// get the patient id
			String patientId = ExcelUtil.getStringValue(row.getCell(0));
			// skip if patient id is empty
			if (StringUtils.isEmpty(patientId)) {
				continue;
			}
			// echo progress
			if (row.getRowNum() % 100 == 0) {
				log.debug("Processing Row: " + row.getRowNum() + " of " + lastRow);
			}
			// process the row
			processRow(row, params, dataSetId, conn);
		}
		// done
		log.debug("Done uploading data for sheet: " + sheet.getSheetName());
	}

	private void processRow(Row row, ArrayList<String> params, String dataSetId, Connection conn) {
		addPatient(row, dataSetId, conn);
		addPatientParams(row, params, dataSetId, conn);
		// TODO: ADD PARAMS
	}

	private PatientDvo addPatient(Row row, String dataSetId, Connection conn) {
		String patientId = ExcelUtil.getStringValue(row.getCell(0));
		String guid = GuidFactory.getGuid();
		// add patient
		PatientDvo dvo = new PatientDvo();
		dvo.setGuid(guid);
		dvo.setDataSetId(dataSetId + "");
		dvo.setPatientId(patientId);
		dvo = Dao.insert(dvo, "guid", guid, conn);
		return dvo;
	}

	private void addPatientParams(Row row, ArrayList<String> params, String dataSetId, Connection conn) {
		
	}

	private ArrayList<String> getParameterNames(Sheet sheet) {
		ArrayList<String> rtn = new ArrayList<String>();
		Row row = sheet.getRow(0);
		Iterator<Cell> cells = ExcelUtil.getCells(row);
		while (cells.hasNext()) {
			Cell cell = cells.next();
			String val = cell.getStringCellValue();
			rtn.add(val);
		}
		return rtn;
	}

}
