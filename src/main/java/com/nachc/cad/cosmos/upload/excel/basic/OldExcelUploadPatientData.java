package com.nachc.cad.cosmos.upload.excel.basic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.yaorma.dao.Dao;

import com.nach.core.util.excel.ExcelUtil;
import com.nach.core.util.excel.enumeration.ExcelCellType;
import com.nach.core.util.guid.GuidFactory;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.dvo.PatientAttDvo;
import com.nachc.cad.cosmos.dvo.PatientAttTypeDvo;
import com.nachc.cad.cosmos.dvo.PatientDvo;
import com.nachc.cad.cosmos.util.proxy.PatientAttTypeProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OldExcelUploadPatientData {

	/**
	 * 
	 * Uploads a single Excel workbook with the following assumptions - First col is
	 * the patient id - All other columns are simple properties of the patient -
	 * First row is the names of the parameters
	 * 
	 */
	public static void uploadPatientData(Sheet sheet, DataSetDvo dataSetDvo, Connection conn) {
		// get the parameter names from the first row of the sheet and add them to the database
		log.debug("Getting params");
		ArrayList<String> paramNames = getParameterNames(sheet);
		ArrayList<PatientAttTypeDvo> params = addPatientAttTypes(paramNames, dataSetDvo, conn);
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
			processRow(row, params, dataSetDvo.getId(), conn);
		}
		// done
		log.debug("Done uploading data for sheet: " + sheet.getSheetName());
	}

	//
	// process row
	//

	private static void processRow(Row row, ArrayList<PatientAttTypeDvo> params, String dataSetId, Connection conn) {
		PatientDvo patientDvo = addPatient(row, dataSetId, conn);
		addPatientAtts(row, params, dataSetId, patientDvo, conn);
	}

	//
	// get the list of parameter names
	//
	
	private static ArrayList<String> getParameterNames(Sheet sheet) {
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

	// ------------------------------------------------------------------------
	//
	// inserts
	//
	// ------------------------------------------------------------------------
	
	//
	// add att types
	//

	private static ArrayList<PatientAttTypeDvo> addPatientAttTypes(ArrayList<String> params, DataSetDvo dataSetDvo, Connection conn) {
		log.info("Adding " + params.size() + " attribute types");
		String projectId = dataSetDvo.getProjectId();
		ArrayList<PatientAttTypeDvo> rtn = new ArrayList<PatientAttTypeDvo>();
		for (int i = 0; i < params.size(); i++) {
			String param = params.get(i);
			PatientAttTypeDvo dvo = PatientAttTypeProxy.findForProjectAndCode(projectId, param, conn);
			if(dvo == null) {
				dvo = new PatientAttTypeDvo();
				dvo.setProjectId(projectId);
				dvo.setCode(param);
				dvo.setName(param);
				dvo.setGuid(GuidFactory.getGuid());
				dvo = Dao.insert(dvo, "guid", dvo.getGuid(), conn);
			}
			rtn.add(dvo);
		}
		log.info("Added  " + rtn.size() + " attribute types");
		return rtn;
	}

	//
	// add the patient
	//

	private static PatientDvo addPatient(Row row, String dataSetId, Connection conn) {
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

	//
	// add patient attributes
	//
	
	private static void addPatientAtts(Row row, ArrayList<PatientAttTypeDvo> params, String dataSetId, PatientDvo patientDvo, Connection conn) {
		String patientId = ExcelUtil.getStringValue(row.getCell(0));
		for(int i=0;i<params.size();i++) {
			// get the cell and the att
			Cell cell = row.getCell(i);
			String cellVal = ExcelUtil.getStringValue(cell);
			if(cellVal == null) {
				continue;
			}
			PatientAttTypeDvo att = params.get(i);
			// create the dvo
			PatientAttDvo dvo = new PatientAttDvo();
			// set dvo values
			dvo.setAttTypeId(att.getId());
			dvo.setDataSetId(dataSetId);
			dvo.setPatientId(patientDvo.getId());
			dvo.setStringVal(cellVal);
			ExcelCellType type = ExcelUtil.getCellType(cell);
			if(type.equals(ExcelCellType.DATE_TIME)) {
				dvo.setDateVal(cellVal);
			} else if(type.equals(ExcelCellType.NUMBER)) {
				dvo.setNumVal(cellVal);
			}
			Dao.insert(dvo, conn);
		}
	}
}
