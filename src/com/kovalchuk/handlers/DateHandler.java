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
    public final static String DATE_FORMAT = "y-M-d";/*"2016-08-16"*/
    private final static String DEFAULT_TIME_ZONE="Europe/Kiev";//Часова зона по-замовчуванню
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

        boolean chekLeap= localDate.isLeapYear();//Чи рік високосний
        int amountOfDays=localDate.getMonth().length(chekLeap);//Кількість днів у місяці
        int dayOfMonth=localDate.getDayOfMonth();//Номер дня місяця
        int numberOfFirstDayMonth=localDate.minusDays(dayOfMonth-1).getDayOfWeek().getValue();//Номер дня тижня(першого числа місяця)

        int amountOfRow=(amountOfDays+numberOfFirstDayMonth)/7 + 1 +(((amountOfDays+numberOfFirstDayMonth)%7==0) ? 0:1);//кількість рядків
        int amountOfColumn=7;//кількість стовпців
        String[][] curMonth=new String[amountOfRow][amountOfColumn];
        //Перший рядок - Дні тижня
        for(int i=0;i<amountOfColumn;i++){
            curMonth[0][i]= DayOfWeek.of(i+1).toString().substring(0,3);
        }
        //boolean chekBegin=false;//Перевіряємо чи вже був старт лічильника
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
