package com.kathi.project.tracker;

public class Log {

    private String location;
    private String name;
    private String time;
    private String date;

    public Log(){
    }

    public Log(String date, String location, String name, String time) {
        this.location = location;
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
