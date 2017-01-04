package com.kovalchuk.handlers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by scorpion on 04.01.17.
 */
public class DateHandler {
    public final static String DATE_FORMAT = "y-M-d";/*Example : "2016-08-16"*/
    private final static String DEFAULT_TIME_ZONE="Europe/Kiev";//Time zone by default
    private LocalDate localDate;

    public DateHandler(){
        this.localDate= getDefaultDate();
    }

    public DateHandler(LocalDate localDate){
        this.localDate=localDate;
    }

    public DateHandler(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        //convert String to LocalDate
        if(isDateValid(date)) {
            localDate = LocalDate.parse(date, formatter);
        }
        else {
            localDate = LocalDate.parse(getDefaultDate().toString(), formatter);
        }
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    private static LocalDate getDefaultDate(){
        return LocalDate.now(Clock.system(ZoneId.of(DEFAULT_TIME_ZONE)));
    }

    public String[][] getCurrentMonthAsMatrix() {

        boolean chekLeap= localDate.isLeapYear();//Is it a leap year?
        int amountOfDays=localDate.getMonth().length(chekLeap);//The number of days in the month
        int dayOfMonth=localDate.getDayOfMonth();//Number of the month
        int numberOfFirstDayMonth=localDate.minusDays(dayOfMonth-1).getDayOfWeek().getValue();//Number of first day of the week
        int amountOfRow=(amountOfDays+numberOfFirstDayMonth)/7 + 1 +(((amountOfDays+numberOfFirstDayMonth)%7==0) ? 0:1);//number of lines
        int amountOfColumn=7;//number of columns
        String[][] curMonth=new String[amountOfRow][amountOfColumn];
        //First row - Days of Week
        for(int i=0;i<amountOfColumn;i++){
            curMonth[0][i]= DayOfWeek.of(i+1).toString().substring(0,3);
        }
        int counterDays=1;
        for(int i=0;i<amountOfRow-1;i++)
        {
            for (int j=0;j<amountOfColumn;j++){
                if(counterDays>1 && counterDays<=amountOfDays){
                    curMonth[i+1][j]=String.valueOf(counterDays);
                    counterDays++;
                }
                else{
                    if(j==(numberOfFirstDayMonth-1) && counterDays==1) {
                        curMonth[i+1][j]=String.valueOf(counterDays);
                        counterDays++;
                    }
                    else curMonth[i+1][j]="";
                }
            }
        }
        return curMonth;
    }

    public boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
