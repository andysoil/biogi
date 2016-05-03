package com.example.soil.biogi.healthCheck;

/**
 * Created by soil on 2016/5/3.
 */
public class healthModel {
    private String date,value ;
    private Integer count ;
    public healthModel(String date, String value,Integer count){
        this.date = date ;
        this.value =value ;
        this.count = count ;
    }
    public String getDate(){return  date;}
    public void setDate(String date){this.date = date ;}
    public String getValue(){
        return value ;
    }
    public void setValue(String value){
        this.value =value ;
    }
    public int getCount(){return  count;}
    public void setCount(Integer count){this.count =count;}

}
