package com.kovalchuk.examplexecution;

import com.kovalchuk.readers.DateReader;
import com.kovalchuk.handlers.DateHandler;
import com.kovalchuk.printers.PrintColorText;

/**
 * Created by scorpion on 03.01.17.
 */
public class Program {
    public static void main(String[] args) {
        String date= DateReader.readDateFromConsole(DateHandler.DATE_FORMAT);
        DateHandler dateHandler=new DateHandler(date);
        PrintColorText.printMonth(dateHandler.getCurrentMonthAsMatrix(),
                dateHandler.getLocalDate().getDayOfMonth(),
                dateHandler.getLocalDate().getMonth().toString()+" "+dateHandler.getLocalDate().getYear()
        );
    }

}
