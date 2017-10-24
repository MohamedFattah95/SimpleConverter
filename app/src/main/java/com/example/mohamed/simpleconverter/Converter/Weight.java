package com.example.mohamed.simpleconverter.Converter;

public class Weight {
    public static double gramToKilogram(double num) {
        return num / 1000;
    }

    public static double kilogramToGram(double num) {
        return num * 1000;
    }

    public static double kilogramToPound(double num) {
        return num * 2.204623;
    }

    public static double poundTokilogram(double num) {
        return num / 2.2046230;
    }

    public static double poundToTonne(double num) {
        return num / 2204.6226218488;
    }

    public static double tonneToPound(double num) {
        return num * 2204.6226218488;
    }
}
