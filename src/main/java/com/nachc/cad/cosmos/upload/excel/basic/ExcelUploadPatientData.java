package com.nachc.cad.cosmos.upload.excel.basic;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.yaorma.database.dao.Dao;

import com.nach.core.util.excel.ExcelUtil;
import com.nach.core.util.guid.GuidFactory;
import com.nachc.cad.cosmos.dao.PatientDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadPatientData {

	public void uploadPatientData(InputStream in, String sheetName, Connection conn) {
		log.debug("Getting sheet");
		Sheet sheet = ExcelUtil.getSheet(in, sheetName);
		log.debug("Got sheet: " + sheet.getSheetName());
		log.debug("Getting params");
		ArrayList<String> params = getParameterNames(sheet);
		log.debug("Getting rows");
		Iterator<Row> rows = ExcelUtil.getRows(sheet);
		int lastRow = sheet.getLastRowNum();
		log.debug("Uploading rows...");
		while(rows.hasNext()) {
			Row row = rows.next();
			if(row.getRowNum() == 0) {
				continue;
			}
			String patientId = ExcelUtil.getStringValue(row.getCell(0));
			if(StringUtils.isEmpty(patientId)) {
				continue;
			}
			if(row.getRowNum() % 100 == 0) {
				log.debug("Processing Row: " + row.getRowNum() + " of " + lastRow);
			}
			processRow(row, params, conn);
		}
		log.debug("Done uploading data for sheet: " + sheet.getSheetName());
	}

	private void processRow(Row row, ArrayList<String> params, Connection conn) {
		addPatient(row, params, conn);
	}

	private void addPatient(Row row, ArrayList<String> params, Connection conn) {
		String patientId = ExcelUtil.getStringValue(row.getCell(0));
		String patientIdType = params.get(0);
		String guid = GuidFactory.getGuid();
		// add patient
		PatientDvo dvo = new PatientDvo();
		dvo.setPatientId(patientId);
		dvo.setGuid(guid);
		Dao.insert(dvo, conn);
	}
	
	private ArrayList<String> getParameterNames(Sheet sheet) {
		ArrayList<String> rtn = new ArrayList<String>();
		Row row = sheet.getRow(0);
		Iterator<Cell> cells = ExcelUtil.getCells(row);
		while(cells.hasNext()) {
			Cell cell = cells.next();
			String val = cell.getStringCellValue();
			rtn.add(val);
		}
		return rtn;
	}

	
	
}
