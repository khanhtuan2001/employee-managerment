package com.tuan.employeemanagement.utilities;

import java.time.format.DateTimeFormatter;

public class isValiDate {
    public static boolean isValidDate(String dateString,String formatString) {
        boolean isvalid;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        try{
            formatter.parse(dateString);
            isvalid = true;

        }catch (Exception e){
            isvalid = false;

        }
        return isvalid;

    }

}
