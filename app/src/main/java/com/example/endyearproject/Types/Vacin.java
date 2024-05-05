package com.example.endyearproject.Types;

import java.util.Date;

public class Vacin {
    private String placeName;
    private Date date;

    public Vacin()
    {
        placeName = "";
        date = new Date();
    }
    public Vacin(String placeName , Date date)
    {
        this.placeName = placeName;
        this.date = date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }
    public Date getDate()
    {
        return this.date;
    }
    public String getPlaceName()
    {
        return this.placeName;
    }
}
