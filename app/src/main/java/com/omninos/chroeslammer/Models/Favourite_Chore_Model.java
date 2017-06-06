package com.omninos.chroeslammer.Models;

/**
 * Created by sandeep on 12-05-2017.
 */

public class Favourite_Chore_Model
{
    String name,time,detail;


    public Favourite_Chore_Model(String name, String time, String detail) {
        this.name = name;
        this.time = time;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
