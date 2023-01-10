package com.exportImportexcel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String formatDate(Date date) {
        DateFormat dateFormat= new SimpleDateFormat("YYYY-MM-DD");
//        regex
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }
}
