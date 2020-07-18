package com.example.demo.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    public static Date stringToDate(String dateString) {
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToString(Date date) {
        return df.format(date);
    }
}
