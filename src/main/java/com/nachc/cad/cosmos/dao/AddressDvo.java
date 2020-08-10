//
// Data Value Object (DVO) for address
//

package com.nachc.cad.cosmos.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.yaorma.dvo.Dvo;

public class AddressDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "address";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "id",
        "street1",
        "street2",
        "city",
        "county",
        "state",
        "zip",
        "lat",
        "lon"
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
        "street1",
        "street2",
        "city",
        "county",
        "state",
        "zip",
        "lat",
        "lon"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "Id",
        "Street1",
        "Street2",
        "City",
        "County",
        "State",
        "Zip",
        "Lat",
        "Lon"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private String id;
    
    private String street1;
    
    private String street2;
    
    private String city;
    
    private String county;
    
    private String state;
    
    private String zip;
    
    private String lat;
    
    private String lon;
    
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
    
    // street1
    
    public void setStreet1(String str) {
        this.street1 = str;
    }
    
    public String getStreet1() {
        return this.street1;
    }
    
    // street2
    
    public void setStreet2(String str) {
        this.street2 = str;
    }
    
    public String getStreet2() {
        return this.street2;
    }
    
    // city
    
    public void setCity(String str) {
        this.city = str;
    }
    
    public String getCity() {
        return this.city;
    }
    
    // county
    
    public void setCounty(String str) {
        this.county = str;
    }
    
    public String getCounty() {
        return this.county;
    }
    
    // state
    
    public void setState(String str) {
        this.state = str;
    }
    
    public String getState() {
        return this.state;
    }
    
    // zip
    
    public void setZip(String str) {
        this.zip = str;
    }
    
    public String getZip() {
        return this.zip;
    }
    
    // lat
    
    public void setLat(String str) {
        this.lat = str;
    }
    
    public String getLat() {
        return this.lat;
    }
    
    // lon
    
    public void setLon(String str) {
        this.lon = str;
    }
    
    public String getLon() {
        return this.lon;
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
