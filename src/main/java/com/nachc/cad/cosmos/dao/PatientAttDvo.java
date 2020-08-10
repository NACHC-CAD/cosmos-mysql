//
// Data Value Object (DVO) for patient_att
//

package com.nachc.cad.cosmos.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.yaorma.dvo.Dvo;

public class PatientAttDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "patient_att";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "id",
        "att_type_id",
        "int_val",
        "code_val",
        "text_val",
        "date_val",
        "patient_att_type"
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
        "attTypeId",
        "intVal",
        "codeVal",
        "textVal",
        "dateVal",
        "patientAttType"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "Id",
        "AttTypeId",
        "IntVal",
        "CodeVal",
        "TextVal",
        "DateVal",
        "PatientAttType"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String id;
    
    private String attTypeId;
    
    private String intVal;
    
    private String codeVal;
    
    private String textVal;
    
    private String dateVal;
    
    private String patientAttType;
    
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
    
    // attTypeId
    
    public void setAttTypeId(String str) {
        this.attTypeId = str;
    }
    
    public String getAttTypeId() {
        return this.attTypeId;
    }
    
    // intVal
    
    public void setIntVal(String str) {
        this.intVal = str;
    }
    
    public String getIntVal() {
        return this.intVal;
    }
    
    // codeVal
    
    public void setCodeVal(String str) {
        this.codeVal = str;
    }
    
    public String getCodeVal() {
        return this.codeVal;
    }
    
    // textVal
    
    public void setTextVal(String str) {
        this.textVal = str;
    }
    
    public String getTextVal() {
        return this.textVal;
    }
    
    // dateVal
    
    public void setDateVal(String str) {
        this.dateVal = str;
    }
    
    public String getDateVal() {
        return this.dateVal;
    }
    
    // patientAttType
    
    public void setPatientAttType(String str) {
        this.patientAttType = str;
    }
    
    public String getPatientAttType() {
        return this.patientAttType;
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
