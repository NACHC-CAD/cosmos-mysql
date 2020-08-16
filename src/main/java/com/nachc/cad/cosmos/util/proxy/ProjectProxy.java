package com.nachc.cad.cosmos.util.proxy;

import java.sql.Connection;

import org.yaorma.dao.Dao;

import com.nachc.cad.cosmos.dvo.ProjectDvo;

public class ProjectProxy {

	public static ProjectDvo findTestingProject(Connection conn) {
		String name = "Testing";
		ProjectDvo dvo = new ProjectDvo();
		dvo = Dao.find(dvo, "name", "Testing: Contraception", conn);
		return dvo;
	}

}
