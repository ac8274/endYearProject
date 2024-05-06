package com.example.endyearproject.Types;

import java.util.Calendar;

public class Vacin {
    private String placeName;
    private Calendar date;

    public Vacin()
    {
        placeName = "";
        date = Calendar.getInstance();
    }
    public Vacin(String placeName , Calendar date)
    {
        this.placeName = placeName;
        this.date = date;
    }
    public void setDate(Calendar date)
    {
        this.date = date;
    }
    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }
    public Calendar getDate()
    {
        return this.date;
    }
    public String getPlaceName()
    {
        return this.placeName;
    }
}
