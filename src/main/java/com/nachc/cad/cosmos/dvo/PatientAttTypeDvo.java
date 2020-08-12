//
// Data Value Object (DVO) for patient_att_type
//

package com.nachc.cad.cosmos.dvo;

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
        "code",
        "data_set_id",
        "description",
        "guid",
        "id",
        "name"
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
        "code",
        "dataSetId",
        "description",
        "guid",
        "id",
        "name"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "Code",
        "DataSetId",
        "Description",
        "Guid",
        "Id",
        "Name"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String code;
    
    private String dataSetId;
    
    private String description;
    
    private String guid;
    
    private String id;
    
    private String name;
    
    //
    // trivial getters and setters
    //
    
    // code
    
    public void setCode(String str) {
        this.code = str;
    }
    
    public String getCode() {
        return this.code;
    }
    
    // dataSetId
    
    public void setDataSetId(String str) {
        this.dataSetId = str;
    }
    
    public String getDataSetId() {
        return this.dataSetId;
    }
    
    // description
    
    public void setDescription(String str) {
        this.description = str;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    // guid
    
    public void setGuid(String str) {
        this.guid = str;
    }
    
    public String getGuid() {
        return this.guid;
    }
    
    // id
    
    public void setId(String str) {
        this.id = str;
    }
    
    public String getId() {
        return this.id;
    }
    
    // name
    
    public void setName(String str) {
        this.name = str;
    }
    
    public String getName() {
        return this.name;
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
