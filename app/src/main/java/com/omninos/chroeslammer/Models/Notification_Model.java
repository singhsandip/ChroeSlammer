package com.omninos.chroeslammer.Models;

/**
 * Created by sandeep on 12-05-2017.
 */

public class Notification_Model
{
    public Notification_Model(String name, String time) {
        this.name = name;
        this.time = time;
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

    String name,time;
}
