package com.nachc.cad.cosmos.upload.excel.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import com.nach.core.util.excel.ExcelUtil;
import com.nach.core.util.guid.GuidFactory;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.dvo.PatientAttTypeDvo;
import com.nachc.cad.cosmos.util.proxy.PatientAttTypeProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadPatientData_DRAFT {

	public static void uploadPatientData(Sheet sheet, DataSetDvo dataSetDvo, Connection conn) {
		List<String> parameterNames = getParameterNames(sheet);
		Map<Integer, String> patientGuids = addPatients(sheet, dataSetDvo, conn);
		List<PatientAttTypeDvo> attributeTypes = getPatientAttTypes(parameterNames, dataSetDvo, conn);
		// addAttributes(attributeTypes, patientGuids, sheet, dataSetDvo, conn);
	}

	//
	// method to get the list of parameters
	//

	private static List<String> getParameterNames(Sheet sheet) {
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

	//
	// method to get the attribute types (inserted if they don't exist yet)
	//

	private static List<PatientAttTypeDvo> getPatientAttTypes(List<String> params, DataSetDvo dataSetDvo, Connection conn) {
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

	private static Map<Integer, String> addPatients(Sheet sheet, DataSetDvo dataSetDvo, Connection conn) {
		log.info("Adding patients");
		HashMap<Integer, String> rtn = new HashMap<Integer, String>();
		Iterator<Row> rows = ExcelUtil.getRows(sheet);
		String sqlString = "insert into patient (guid, data_set_id, patient_id, patient_id_type) values (?,?,?,?)";
		PreparedStatement ps = Database.getPreparedStatement(sqlString, conn);

		PreparedStatement attPs = Database.getPreparedStatement(sqlString, conn);
		log.info("Reading spread sheet");
		while (rows.hasNext()) {
			Row row = rows.next();
			// skip first row (it is the header information)
			if (row.getRowNum() == 0) {
				continue;
			}
			String patientId = ExcelUtil.getStringValue(row.getCell(0));
			if (StringUtils.isEmpty(patientId)) {
				continue;
			}
			String guid = GuidFactory.getGuid();
			rtn.put(row.getRowNum(), guid);
			ArrayList<String> params = new ArrayList<String>();
			params.add(guid);
			params.add(dataSetDvo.getId());
			params.add(patientId);
			params.add(null);
			Database.addToBatch(params, ps);
		}
		log.info("Executing batch update");
		Database.execute(ps);
		log.info("Done with batch update");
		return rtn;
	}

}
