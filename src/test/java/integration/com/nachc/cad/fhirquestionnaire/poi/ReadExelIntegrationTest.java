package integration.com.nachc.cad.fhirquestionnaire.poi;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadExelIntegrationTest {

	@Test
	public void shouldReadFile() {
		try {
			log.info("Starting test...");
			InputStream in = FileUtil.getInputStream("/files/excel/FHIR-Questionnaire-COVID19-2020-08-02.xlsx");
			log.info("Got inputstream: " + in); 
			assertTrue(in != null);
			Workbook book = new XSSFWorkbook(in);
			Sheet sheet = book.getSheet("FHIR-Template");
			log.info("Got sheet: " + sheet.getSheetName());
			Iterator<Row> rows = sheet.iterator();
			log.info("Workbook contents:\n");
			while(rows.hasNext()) {
				Row row = rows.next();
				Iterator<Cell> cells = row.cellIterator();
				String rowText = "";
				while(cells.hasNext()) {
					Cell cell = cells.next();
					String str = cell.getStringCellValue();
					rowText += str + "\t";
				}
				System.out.println(rowText);
			}
			System.out.println("============================\nEND OF WORKBOOK");
			System.out.println("\n\n\n");
			log.info("Done.");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
