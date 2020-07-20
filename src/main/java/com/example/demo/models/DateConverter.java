package com.example.demo.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    private static final SimpleDateFormat _df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    public static Date stringToDate(String dateString) {
        try {
            return _df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToString(Date date) {
        return _df.format(date);
    }
}
