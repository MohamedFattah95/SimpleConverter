package com.example.mohamed.simpleconverter.Converter;

public class Area {
    public static double sqCMToSqM(double num) {
        return num / 10000;
    }

    public static double sqMToSqKM(double num) {
        return num / 1000000;
    }

    public static double sqKMToSqI(double num) {
        return num * (1.5500031 * Math.pow(10, 9));
    }

    public static double sqIToSqF(double num) {
        return num / 144;
    }

    public static double sqFToSqY(double num) {
        return num / 9;
    }

    public static double sqYToSqMi(double num) {
        return num / 3097600;
    }

    public static double sqMiToAcre(double num) {
        return num * 640;
    }

    public static double sqMToSqCM(double num) {
        return num * 10000;
    }

    public static double sqKMToSqM(double num) {
        return num * 1000000;
    }

    public static double sqIToSqKM(double num) {
        return num / (1.5500031 * Math.pow(10, 9));
    }

    public static double sqFToSqI(double num) {
        return num * 144;
    }

    public static double sqYToSqF(double num) {
        return num * 9;
    }

    public static double sqMiToSqY(double num) {
        return num * 3097600;
    }

    public static double acreToSqMi(double num) {
        return num / 640;
    }
}
