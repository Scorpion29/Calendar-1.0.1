package com.kovalchuk.printers;

/**
 * Created by scorpion on 04.01.17.
 */
public class PrintColorText {
    public static final String RED = "\u001B[31m";//Color for the weekend
    public static final String GREEN = "\u001B[32m";//Color for current date
    public static final String RESET = "\u001B[0m";//Color by default

    private static String getColorWeekend(){
        return RED;
    }

    private static String getColorToday(){
        return GREEN;
    }

    private static String getColorConsole(){
        return RESET;
    }

    public static void printMonth(String[][] matrix,int numberDayOfMonth,String title){
        if(title!=null) {
            System.out.println(title);
        }
        String currentColor=getColorConsole();
        String tabulation="";
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++) {
                //To a different color for holidays
                if(j>matrix[i].length-3){
                    currentColor=getColorWeekend();
                }
                //To a different color for the current day
                if(matrix[i][j].equals(String.valueOf(numberDayOfMonth))){
                    currentColor=getColorToday();
                }
                //Tab for all items except the last
                if (j != matrix[i].length - 1) {
                    tabulation="\t";
                }
                else{
                    tabulation="";
                }
                //Outout
                System.out.print(currentColor+matrix[i][j] + tabulation);
                //Restores color console
                if(!currentColor.equals(RESET)){
                    currentColor=getColorConsole();
                }
            }
            System.out.println();
        }
        System.out.print(getColorConsole());
    }
}
