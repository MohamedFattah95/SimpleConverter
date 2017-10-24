package com.example.mohamed.simpleconverter.Converter;

 public class Temperature {

    public static double fahrenheitToKelvin(double num) {
        return (num + 459.67) * 5 / 9;
    }

    public static double kelvinToFahrenheit(double num) {
        return num * 9 / 5 - 459.67;
    }

    public static double kelvinToCelsius(double num) {
        return num - 273.15;
    }

    public static double celsiusToKelvin(double num) {
        return num + 273.15;
    }

}
