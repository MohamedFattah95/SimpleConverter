package com.example.mohamed.simpleconverter.Converter;

public class Length {

    public static double micrometerToNanometre(double num) {
        return num * 1000;
    }

    public static double millimeterToMicrometer(double num) {
        return num * 1000;
    }

    public static double centimeterToMillimeter(double num) {
        return num * 10;
    }

    public static double meterToCentimeter(double num) {
        return num * 100;
    }

    public static double kilometerToMeter(double num) {
        return num * 1000;
    }

    public static double mileToKilometer(double num) {
        return num * 1.609344;
    }

    public static double nauticalMaileToMile(double num) {
        return num * 1.15077945;
    }

    public static double inchToNauticalmile(double num) {
        return num / 72913.3858;
    }

    public static double feetToInch(double num) {
        return num * 12;
    }

    public static double yardToFeet(double num) {
        return num *3;
    }

    public static double lightYearToYard(double num) {
        return num *(1.03461597 * Math.pow(10,16));
    }


    public static double nanometreToMicrometer(double num) {
        return num / 1000;
    }

    public static double micrometerToMillimeter(double num) {
        return num / 1000;
    }

    public static double millimeterToCentimeter(double num) {
        return num / 10;
    }

    public static double centimeterToMeter(double num) {
        return num / 100;
    }

    public static double meterToKilometer(double num) {
        return num / 1000;
    }

    public static double kilometerToMile(double num) {
        return num * 0.621371192;
    }

    public static double mileToNauticalMaile(double num) {
        return num * 0.868976242;
    }

    public static double nauticalmileToInch(double num) {
        return num * 72913.3858;
    }

    public static double inchToFeet(double num) {
        return num / 12;
    }

    public static double feetToYard(double num) {
        return num /3;
    }

    public static double yardToLightYear(double num) {
        return num *(9.66542207 * Math.pow(10,-17));
    }
}
