package com.company;

public class Date {

    private static final Date dateObject = new Date();
    int date = 0;

    /**
     * Use private constructor to avoid multiple instantiation of Date object (Singlton Pattern)
     */
    private Date() {
    }

    public static Date getDate() {
        if (dateObject == null) {
            return new Date();
        }
        return dateObject;
    }

    public int getCurrentDate(){
        return date;
    }

    public boolean setCurrentDate(int newDate){
        if(newDate >= 0 && newDate <= 364) {
            date = newDate;
            return true;
        } else {
            return false;
        }
    }

}
