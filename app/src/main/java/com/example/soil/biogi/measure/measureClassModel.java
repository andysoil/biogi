package com.example.soil.biogi.measure;

/**
 * Created by soil on 2016/4/16.
 */
public class measureClassModel {
    private String _id,year,name ;
    public measureClassModel(String name, String _id){
        this._id =_id ;
        this.name = name ;
        this.year =year ;
    }
    public String getName(){return name;}
    public void setName(String title){this.name = title ;

    }
    public String getYear(){return year;}

    public void setYear(String year){
        this.year = year ;

    }
    public String get_id(){
        return _id ;
    }
    public void setValues(String values) {
        this._id = values;
    }
}
