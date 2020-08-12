package com.nachc.cad.cosmos.util.proxy;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

import com.nach.core.util.guid.GuidFactory;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.dvo.ProjectDvo;
import com.nachc.cad.cosmos.util.conn.ConnectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatasetProxyIntegrationTest {

	@Test
	public void shouldCreateDataSets() {
		log.info("Starting test...");
		log.info("Getting connection");
		Connection conn = ConnectionUtil.getMysqlConnection();
		log.info("Getting prject");
		ProjectDvo projectDvo = ProjectProxy.findTestingProject(conn);
		for(int i=0;i<10;i++) {
			DataSetDvo dvo = DataSetProxy.createDataSet("Test-" + GuidFactory.getGuid(), "Test dataset", projectDvo.getId(), conn);
			log.info("Created dataset: " + dvo.getId() + ": " + dvo.getGuid());
			assertTrue(dvo.getId() != null);
		}
		log.info("Done with test 1.\n\n");
	}
	
	@Test
	public void shouldCreateTestDataSets() {
		log.info("Starting test...");
		log.info("Getting connection");
		Connection conn = ConnectionUtil.getMysqlConnection();
		for(int i=0;i<10;i++) {
			DataSetDvo dvo = DataSetProxy.createTestDataSet(conn);
			log.info("Created dataset: " + dvo.getId() + ": " + dvo.getGuid());
			assertTrue(dvo.getId() != null);
		}
		log.info("Done with test 2.\n\n");
	}
	
}
