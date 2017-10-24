package com.example.mohamed.simpleconverter.Converter;

public class DigitalStorage {
    public static double byteToBit(double num) {
        return num * 8;
    }
    public static double bitToByte(double num) {
        return num / 8;
    }

    public static double multiply(double num, int i) {
        if (i == 0)
            return num;
        return multiply(num, i - 1) * 1024;
    }
    public static double division(double num, int i) {
        if (i == 0)
            return num;
        return division(num, i - 1) / 1024;
    }
}
