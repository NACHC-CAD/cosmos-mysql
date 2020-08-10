//
// Data Value Object (DVO) for patient_id
//

package com.nachc.cad.cosmos.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.yaorma.dvo.Dvo;

public class PatientIdDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "patient_id";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "id",
        "data_import_id",
        "patient_id",
        "patient_id_type"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "id",
        "dataImportId",
        "patientId",
        "patientIdType"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "Id",
        "DataImportId",
        "PatientId",
        "PatientIdType"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String id;
    
    private String dataImportId;
    
    private String patientId;
    
    private String patientIdType;
    
    //
    // trivial getters and setters
    //
    
    // id
    
    public void setId(String str) {
        this.id = str;
    }
    
    public String getId() {
        return this.id;
    }
    
    // dataImportId
    
    public void setDataImportId(String str) {
        this.dataImportId = str;
    }
    
    public String getDataImportId() {
        return this.dataImportId;
    }
    
    // patientId
    
    public void setPatientId(String str) {
        this.patientId = str;
    }
    
    public String getPatientId() {
        return this.patientId;
    }
    
    // patientIdType
    
    public void setPatientIdType(String str) {
        this.patientIdType = str;
    }
    
    public String getPatientIdType() {
        return this.patientIdType;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    };
    
    public String[] getPrimaryKeyColumnNames() {
        return PRIMARY_KEY_COLUMN_NAMES;
    };
    
    public String[] getJavaNames() {
        return JAVA_NAMES;
    };
    
    public String[] getJavaNamesProper() {
        return JAVA_NAMES_PROPER;
    };
    
    public void setDescriptions(HashMap<String, String> descriptions) {
        this.descriptions = descriptions;
    }
    
    public HashMap<String, String> getDescriptions() {
        return this.descriptions;
    }
    
    public void addDescription(String javaName, String value) {
        this.descriptions.put(javaName, value);
    }
    
    public String getDescription(String javaName) {
        return this.descriptions.get(javaName);
    }
    
}
