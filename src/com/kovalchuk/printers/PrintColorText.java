package com.kovalchuk.printers;

/**
 * Created by scorpion on 04.01.17.
 */
public class PrintColorText {
    public static final String RED = "\u001B[31m";//Колір для вихідних
    public static final String GREEN = "\u001B[32m";//Колір для поточної дати
    public static final String RESET = "\u001B[0m";//Колір по-замовчуванню

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
                //Щоб був інший колір для вихідних
                if(j>matrix[i].length-3){
                    currentColor=getColorWeekend();
                }
                //Щоб був інший колір для поточного дня
                if(matrix[i][j].equals(String.valueOf(numberDayOfMonth))){
                    currentColor=getColorToday();
                }
                //Для того щоб табулювати всі елементи крім останнього
                if (j != matrix[i].length - 1) {
                    tabulation="\t";
                }
                else{
                    tabulation="";
                }
                //Виводимо
                System.out.print(currentColor+matrix[i][j] + tabulation);
                //Відновлюємо консольний колір(Ставимо консольний колір,якщо поточний таким не являється)
                if(!currentColor.equals(RESET)){
                    currentColor=getColorConsole();
                }
            }
            System.out.println();
        }
        System.out.print(getColorConsole());//Вертаємо консольний колір у виведення
    }
}
