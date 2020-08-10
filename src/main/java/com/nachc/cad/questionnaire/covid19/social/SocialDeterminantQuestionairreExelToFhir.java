package com.nachc.cad.questionnaire.covid19.social;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hl7.fhir.dstu3.model.Questionnaire;
import org.hl7.fhir.dstu3.model.Questionnaire.QuestionnaireItemComponent;
import org.hl7.fhir.dstu3.model.Questionnaire.QuestionnaireItemOptionComponent;
import org.hl7.fhir.dstu3.model.Questionnaire.QuestionnaireItemType;
import org.hl7.fhir.dstu3.model.StringType;

import com.nach.core.util.xml.XmlUtil;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocialDeterminantQuestionairreExelToFhir {

	public Questionnaire toHapi(InputStream in) {
		try {
			log.info("Parsing workbook...");
			Questionnaire rtn = new Questionnaire();
			log.info("Got inputstream: " + in);
			Workbook book = new XSSFWorkbook(in);
			Sheet sheet = book.getSheet("FHIR-Template");
			log.info("Got sheet: " + sheet.getSheetName());
			Iterator<Row> rows = sheet.iterator();
			log.info("Parsing sheet...");
			while (rows.hasNext()) {
				QuestionnaireItemComponent question = null;
				Row row = rows.next();
				int rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue;
				}
				String dataType = row.getCell(3).getStringCellValue();
				// add question for each type
				if(dataType == null || StringUtils.isEmpty(dataType)) {
					question= createQuestion(row, QuestionnaireItemType.TEXT);
				}
				else if ("options".equals(dataType.trim().toLowerCase())) {
					question= createQuestion(row, QuestionnaireItemType.OPENCHOICE);
				} else if("date".equals(dataType.trim().toLowerCase())) {
					question= createQuestion(row, QuestionnaireItemType.DATE);
				} else if("datetime".equals(dataType.trim().toLowerCase())) {
					question= createQuestion(row, QuestionnaireItemType.DATETIME);
				} else {
					question= createQuestion(row, QuestionnaireItemType.TEXT);
				}
				// add the question to the questionnaire (e.g. decline to answer)
				rtn.getItem().add(question);
			}
			log.info("Done parsing sheet.");
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public String toXml(InputStream in) {
		Questionnaire quest = toHapi(in);
		FhirContext ctx = FhirContext.forDstu3();
		IParser parser = ctx.newXmlParser();
		String rtn = parser.encodeResourceToString(quest);
		rtn = XmlUtil.prettyPrint(rtn);
		return rtn;
	}

	// ------------------------------------------------------------------------
	//
	// internal implementation (all private past here)
	//
	// ------------------------------------------------------------------------

	//
	// create question
	//

	private QuestionnaireItemComponent createQuestion(Row row, QuestionnaireItemType type) {
		// create the question
		QuestionnaireItemComponent question = new QuestionnaireItemComponent();
		// set the type
		question.setType(type);
		String text = row.getCell(1).getStringCellValue();
		question.setText(text);
		// set the variable name
		String id = row.getCell(0).getStringCellValue();
		question.setId(id);
		question.setLinkId(id);
		// add the options
		addOptions(row, question);
		return question;
	}
	
	//
	// add options
	//
	
	private void addOptions(Row row, QuestionnaireItemComponent question) {
		Iterator<Cell> cells = row.cellIterator();
		while (cells.hasNext()) {
			Cell cell = cells.next();
			if (cell.getColumnIndex() <= 5) {
				continue;
			}
			String val = cell.getStringCellValue();
			if (val != null) {
				val = val.trim();
			}
			QuestionnaireItemOptionComponent option = new QuestionnaireItemOptionComponent();
			option.setValue(new StringType(val));
			question.addOption(option);
		}
	}

}
