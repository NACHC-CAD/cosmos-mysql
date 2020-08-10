package com.nachc.cad.cosmos.upload.excel.basic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.nach.core.util.excel.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUploadPatientData {

	public void uploadPatientData(InputStream in, String sheetName) {
		log.debug("Getting sheet");
		Sheet sheet = ExcelUtil.getSheet(in, sheetName);
		log.debug("Got sheet: " + sheet.getSheetName());
		log.debug("Getting params");
		ArrayList<String> params = getParameterNames(sheet);
		log.debug("Getting rows");
		Iterator<Row> rows = ExcelUtil.getRows(sheet);
		log.debug("Uploading rows...");
		while(rows.hasNext()) {
			Row row = rows.next();
			log.debug("Processing Row: " + row.getRowNum());
			processRow(row, params);
		}
		log.debug("Done uploading data for sheet: " + sheet.getSheetName());
	}

	private void processRow(Row row, ArrayList<String> params) {
		// TODO Auto-generated method stub
		
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
