package com.nachc.cad.cosmos.util.proxy;

import java.sql.Connection;

import org.junit.Test;

import com.nachc.cad.cosmos.dvo.ProjectDvo;
import com.nachc.cad.cosmos.util.conn.ConnectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectProxyIntegrationTest {

	@Test
	public void shouldGetTestProjet() {
		log.info("Starting test...");
		log.info("Getting connection");
		Connection conn = ConnectionUtil.getMysqlConnection();
		log.info("Excecuting query");
		ProjectDvo dvo = ProjectProxy.findTestingProject(conn);
		log.info("Got dvo: " + dvo.getId() + ": " + dvo.getName());
		log.info("Done.");
	}
	
}
