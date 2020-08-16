package com.nachc.cad.cosmos.upload.excel.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import com.nach.core.util.excel.ExcelUtil;
import com.nach.core.util.excel.enumeration.ExcelCellType;
import com.nach.core.util.guid.GuidFactory;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.dvo.PatientAttDvo;
import com.nachc.cad.cosmos.dvo.PatientAttTypeDvo;
import com.nachc.cad.cosmos.util.proxy.PatientAttTypeProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadPatientData {

	/**
	 * 
	 * Uploads a single Excel workbook with the following assumptions - First col is
	 * the patient id - All other columns are simple properties of the patient -
	 * First row is the names of the parameters
	 * 
	 */
	public static void uploadPatientData(Sheet sheet, DataSetDvo dataSetDvo, Connection conn) {
		// get the parameter names from the first row of the sheet and add them to the
		// database
		log.debug("Getting params");
		ArrayList<String> paramNames = getParameterNames(sheet);
		ArrayList<PatientAttTypeDvo> paramTypes = addPatientAttTypes(paramNames, dataSetDvo, conn);
		// iterate through each row of the sheet
		log.debug("Getting rows");
		Iterator<Row> rows = ExcelUtil.getRows(sheet);
		int lastRow = sheet.getLastRowNum();
		// get the prepared statements
		PreparedStatement psForPatInsert = getPreparedStatementForPatientInsert(conn);
		PreparedStatement psForAttInsert = getPreparedStatementForPatientAttributeInsert(conn);
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
				log.debug("Doing patient insert");
				Database.execute(psForPatInsert, false);
				log.debug("Doing attribute insert");
				Database.execute(psForAttInsert, false);
				log.debug("Done with inserts");
			}
			// process the row
			String guid = addPatient(row, dataSetDvo.getId(), psForPatInsert);
			addPatientAtts(row, paramTypes, dataSetDvo.getId(), guid, psForAttInsert);
		}
		// write to the database
		Database.execute(psForPatInsert, true);
		Database.execute(psForAttInsert, true);
		// done
		log.debug("Done uploading data for sheet: " + sheet.getSheetName());
	}

	//
	// process row
	//

	/*
	 * private static void processRow(Row row, ArrayList<PatientAttTypeDvo> params,
	 * String dataSetId, Connection conn) { PatientDvo patientDvo = addPatient(row,
	 * dataSetId, conn); addPatientAtts(row, params, dataSetId, patientDvo, conn); }
	 */

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
			if (dvo == null) {
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

	private static String addPatient(Row row, String dataSetId, PreparedStatement ps) {
		String patientId = ExcelUtil.getStringValue(row.getCell(0));
		String guid = GuidFactory.getGuid();
		// add patient
		ArrayList<String> params = new ArrayList<String>();
		params.add(guid);
		params.add(dataSetId + "");
		params.add(patientId);
		params.add(null);
		Database.addToBatch(params, ps);
		return guid;
	}

	//
	// add patient attributes
	//

	private static void addPatientAtts(Row row, ArrayList<PatientAttTypeDvo> paramTypes, String dataSetId, String patientGuid, PreparedStatement ps) {
		String patientId = ExcelUtil.getStringValue(row.getCell(0));
		for (int i = 0; i < paramTypes.size(); i++) {
			// get the cell and the att
			Cell cell = row.getCell(i);
			String cellVal = ExcelUtil.getStringValue(cell);
			if (cellVal == null) {
				continue;
			}
			PatientAttTypeDvo att = paramTypes.get(i);
			// create the parameters
			ArrayList<String> params = new ArrayList<String>();
			params.add(dataSetId);
			params.add(patientGuid);
			params.add(att.getId());
			params.add(cellVal);
			// set dvo values
			ExcelCellType type = ExcelUtil.getCellType(cell);
			if (type.equals(ExcelCellType.DATE_TIME)) {
				params.add(cellVal);
				params.add(null);
			} else if (type.equals(ExcelCellType.NUMBER)) {
				params.add(null);
				params.add(cellVal);
			} else {
				params.add(null);
				params.add(null);
			}
			Database.addToBatch(params, ps);
		}
	}

	private static PreparedStatement getPreparedStatementForPatientInsert(Connection conn) {
		String sqlString = "insert into patient (guid, data_set_id, patient_id, patient_id_type) values (?,?,?,?)";
		PreparedStatement rtn = Database.getPreparedStatement(sqlString, conn);
		return rtn;
	}

	private static PreparedStatement getPreparedStatementForPatientAttributeInsert(Connection conn) {
		String sqlString = "";
		sqlString += "insert into patient_att (data_set_id, patient_id, att_type_id, string_val, date_val, num_val) ";
		sqlString += "values(?,(select id from patient where guid=?), ?, ?,?,?)";
		PreparedStatement rtn = Database.getPreparedStatement(sqlString, conn);
		return rtn;
	}

}
