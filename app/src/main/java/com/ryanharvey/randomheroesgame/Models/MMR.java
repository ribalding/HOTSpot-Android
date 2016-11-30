package com.ryanharvey.randomheroesgame.Models;

/**
 * Created by Ryan on 11/29/2016.
 */

public class MMR {
    private String type;
    private String number;
    private String dateTime;
    public MMR(){}

    public MMR(String type, String number, String dateTime){
        this.type = type;
        this.number = number;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
