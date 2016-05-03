package com.example.soil.biogi.measure;

/**
 * Created by soil on 2016/4/27.
 */
public class measureItemModel {
    private String value, indate,name ;
    public measureItemModel(String name, String value, String date){
        this.value =value ;
        this.name = name ;
        this.indate =date ;
    }
    public String getName(){return name;}
    public void setName(String title){this.name = title ;

    }
    public String getYear(){return indate;}

    public void setYear(String year){
        this.indate = year ;

    }
    public String getValue(){
        return value;
    }
    public void setValues(String values) {
        this.value = values;
    }
}


