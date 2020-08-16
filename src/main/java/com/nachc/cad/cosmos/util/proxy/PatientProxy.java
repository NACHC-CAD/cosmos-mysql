package com.nachc.cad.cosmos.util.proxy;

import java.sql.Connection;
import java.util.List;

import org.yaorma.dao.Dao;

import com.nachc.cad.cosmos.dvo.DataSetDvo;
import com.nachc.cad.cosmos.dvo.PatientDvo;

public class PatientProxy {

	public static List<PatientDvo> findForDataSet(DataSetDvo dvo, Connection conn) {
		String dataSetId = dvo.getId();
		return findForDataSet(dataSetId, conn);
 	}
	
	public static List<PatientDvo> findForDataSet(String dataSetId, Connection conn) {
		String sqlString = "select * from patient where data_set_id = ?";
		List<PatientDvo> rtn = Dao.findList(new PatientDvo(), sqlString, dataSetId, conn);
		return rtn;
 	}
	
}
