//
// Data Value Object (DVO) for team
//

package com.nachc.cad.cosmos.dvo;

import java.util.ArrayList;
import java.util.HashMap;

import org.yaorma.dvo.Dvo;

public class TeamDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "team";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "id",
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
        "name",
        "description"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "Id",
        "Name",
        "Description"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String id;
    
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
