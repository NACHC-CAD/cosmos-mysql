package developer;

import com.nachc.cad.cosmos.install.CreateDatabase;
import com.nachc.cad.cosmos.upload.excel.basic.ExcelUploadPatientDataIntegrationTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitDevEnv {

	public static void main(String[] args) throws Exception {
		log.info("Creating database");
		CreateDatabase.init();
		log.info("Importing data");
		ExcelUploadPatientDataIntegrationTest.uploadFiles();
		log.info("Done.");
	}

}
