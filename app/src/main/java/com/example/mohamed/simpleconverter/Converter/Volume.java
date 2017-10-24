package com.example.mohamed.simpleconverter.Converter;

public class Volume {
    public static double litreToCubicCM(double num) {
        return num * 1000;
    }

    public static double cubicCMToCubicM(double num) {
        return num / 1000000;
    }

    public static double cubicMToCubicI(double num) {
        return num * 61023.7441;
    }

    public static double cubicIToCubicF(double num) {
        return num / 1728;
    }

    public static double cubicFToCubicY(double num) {
        return num / 27;
    }


    public static double cubicCMToLitre(double num) {
        return num / 1000;
    }

    public static double cubicMToCubicCM(double num) {
        return num * 1000000;
    }

    public static double cubicIToCubicM(double num) {
        return num * 1.6387064 * Math.pow(10, -5);
    }

    public static double cubicFToCubicI(double num) {
        return num * 1728;
    }

    public static double cubicYToCubicF(double num) {
        return num * 27;
    }

}
