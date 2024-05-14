package com.example.endyearproject.Types;

import com.example.endyearproject.MainActivity;

public class Expiremental_None_Intent_Transfer {
    /**
     * Description: Class exists for the purpose of creating an illusion that the app starts a new Activity every time' although it doesn't.
     * this is achieved by setting the destination field to what function should start next. and finishing current intent, which returns to main activity which checks the contents of the destination
     * and starting the activity requested before the main activity screen can show up.
     * @param studentToGiveBack: this parameter is need only for the Edit function to work properly.
     * For more info on Edit Function:
     * @see MainActivity
     */
    public static Student studentToGiveBack=null;
    public static String destination=MenuTitels.add_activity;
    public Expiremental_None_Intent_Transfer()
    {

    }
}
