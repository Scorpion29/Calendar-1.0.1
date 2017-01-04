package com.kovalchuk.readers;

import java.util.Scanner;

/**
 * Created by scorpion on 04.01.17.
 */
public class DateReader {
    public static String readDateFromConsole(String dateFormat){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter date using the format: "+ dateFormat);
        String tempDate="";
        try {
            tempDate=in.nextLine();
        }
        catch (Exception e){
            e.getMessage();
        }
        return tempDate;
    }
}