package com.nachc.cad.questionnaire.covid19.social;

import java.io.InputStream;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocialDeterminantQuestionairreExelToFhirIntegrationTest {

	@Test
	public void shouldCreateFhirResource() {
		log.info("Starting test...");
		log.info("Getting file");
		InputStream in = FileUtil.getInputStream("/files/excel/FHIR-Questionnaire-COVID19-2020-08-02.xlsx");
		log.info("Creating questionnaire");
		SocialDeterminantQuestionairreExelToFhir converter = new SocialDeterminantQuestionairreExelToFhir();
		log.info("Getting xml");
		String xml = converter.toXml(in);
		log.info("Got xml:\n\n" + xml + "\n\n");
		log.info("Done.");
	}
	
}
