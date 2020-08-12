package com.nachc.cad.cosmos.util.proxy;

import java.sql.Connection;

import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import com.nach.core.util.guid.GuidFactory;
import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.dvo.ProjectDvo;

public class DataSetProxy {

	public static DataSetDvo createTestDataSet(Connection conn) {
		ProjectDvo projectDvo = ProjectProxy.findTestingProject(conn);
		return createDataSet(null, null, projectDvo.getId(), conn);
	}

	public static DataSetDvo createDataSet(String name, String description, String projectId, Connection conn) {
		DataSetDvo dvo = new DataSetDvo();
		dvo.setGuid(GuidFactory.getGuid());
		dvo.setDescription(description);
		dvo.setProjectId(projectId);
		Dao.insert(dvo, conn);
		dvo  = Database.find(dvo, "guid", dvo.getGuid(), conn);
		return dvo;
	}

}
