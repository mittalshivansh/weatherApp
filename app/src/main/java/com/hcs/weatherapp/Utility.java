package com.hcs.weatherapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shivanshmittal on 23/08/14.
 */
public  class Utility {

    public static String getFormattedDate(long time){
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("E, MMM d");
        return format.format(date);
    }



}
