//
// Data Value Object (DVO) for patient_att
//

package com.nachc.cad.cosmos.dvo;

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
        "att_type_id",
        "code_val",
        "data_set_id",
        "data_type",
        "date_val",
        "id",
        "num_val",
        "patient_id",
        "ref_code",
        "ref_entity",
        "ref_key",
        "string_val"
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
        "attTypeId",
        "codeVal",
        "dataSetId",
        "dataType",
        "dateVal",
        "id",
        "numVal",
        "patientId",
        "refCode",
        "refEntity",
        "refKey",
        "stringVal"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "AttTypeId",
        "CodeVal",
        "DataSetId",
        "DataType",
        "DateVal",
        "Id",
        "NumVal",
        "PatientId",
        "RefCode",
        "RefEntity",
        "RefKey",
        "StringVal"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String attTypeId;
    
    private String codeVal;
    
    private String dataSetId;
    
    private String dataType;
    
    private String dateVal;
    
    private String id;
    
    private String numVal;
    
    private String patientId;
    
    private String refCode;
    
    private String refEntity;
    
    private String refKey;
    
    private String stringVal;
    
    //
    // trivial getters and setters
    //
    
    // attTypeId
    
    public void setAttTypeId(String str) {
        this.attTypeId = str;
    }
    
    public String getAttTypeId() {
        return this.attTypeId;
    }
    
    // codeVal
    
    public void setCodeVal(String str) {
        this.codeVal = str;
    }
    
    public String getCodeVal() {
        return this.codeVal;
    }
    
    // dataSetId
    
    public void setDataSetId(String str) {
        this.dataSetId = str;
    }
    
    public String getDataSetId() {
        return this.dataSetId;
    }
    
    // dataType
    
    public void setDataType(String str) {
        this.dataType = str;
    }
    
    public String getDataType() {
        return this.dataType;
    }
    
    // dateVal
    
    public void setDateVal(String str) {
        this.dateVal = str;
    }
    
    public String getDateVal() {
        return this.dateVal;
    }
    
    // id
    
    public void setId(String str) {
        this.id = str;
    }
    
    public String getId() {
        return this.id;
    }
    
    // numVal
    
    public void setNumVal(String str) {
        this.numVal = str;
    }
    
    public String getNumVal() {
        return this.numVal;
    }
    
    // patientId
    
    public void setPatientId(String str) {
        this.patientId = str;
    }
    
    public String getPatientId() {
        return this.patientId;
    }
    
    // refCode
    
    public void setRefCode(String str) {
        this.refCode = str;
    }
    
    public String getRefCode() {
        return this.refCode;
    }
    
    // refEntity
    
    public void setRefEntity(String str) {
        this.refEntity = str;
    }
    
    public String getRefEntity() {
        return this.refEntity;
    }
    
    // refKey
    
    public void setRefKey(String str) {
        this.refKey = str;
    }
    
    public String getRefKey() {
        return this.refKey;
    }
    
    // stringVal
    
    public void setStringVal(String str) {
        this.stringVal = str;
    }
    
    public String getStringVal() {
        return this.stringVal;
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
