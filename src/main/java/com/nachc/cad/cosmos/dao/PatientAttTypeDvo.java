//
// Data Value Object (DVO) for patient_att_type
//

package com.nachc.cad.cosmos.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.yaorma.dvo.Dvo;

public class PatientAttTypeDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "patient_att_type";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "id",
        "data_import_id",
        "code",
        "name",
        "description"
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
        "code",
        "name",
        "description"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "Id",
        "DataImportId",
        "Code",
        "Name",
        "Description"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String id;
    
    private String dataImportId;
    
    private String code;
    
    private String name;
    
    private String description;
    
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
    
    // code
    
    public void setCode(String str) {
        this.code = str;
    }
    
    public String getCode() {
        return this.code;
    }
    
    // name
    
    public void setName(String str) {
        this.name = str;
    }
    
    public String getName() {
        return this.name;
    }
    
    // description
    
    public void setDescription(String str) {
        this.description = str;
    }
    
    public String getDescription() {
        return this.description;
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
