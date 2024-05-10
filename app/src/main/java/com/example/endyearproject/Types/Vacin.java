package com.example.endyearproject.Types;

import java.util.Calendar;

public class Vacin {
    private String placeName;
    private String date;

    public Vacin()
    {
        placeName = "";
        date = "";
    }
    public Vacin(String placeName , String date)
    {
        this.placeName = placeName;
        this.date = date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }
    public String getDate()
    {
        return this.date;
    }
    public String getPlaceName()
    {
        return this.placeName;
    }
}
