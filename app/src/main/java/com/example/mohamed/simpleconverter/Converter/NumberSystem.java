package com.example.mohamed.simpleconverter.Converter;

import android.content.Context;

import com.example.mohamed.simpleconverter.R;

import java.math.BigDecimal;

public class NumberSystem {

    private final String error;
    private static char zero;
    private static char dot;

    public NumberSystem(Context c) {
        error = c.getResources().getString(R.string.error_message);
        zero = '0';
        dot = '.';
    }

    private double hexToDecimal(String floatNumber, int base)/*Convert floating Hexadecimal to Decimal*/ {
        double convertedFloat = 0;

        for (int i = 0; i < floatNumber.length(); i++)
            switch (floatNumber.charAt(i)) {
                case 'A':
                case 'a':
                    convertedFloat += 10 * Math.pow(base, (i * -1) - 1);
                    break;
                case 'B':
                case 'b':
                    convertedFloat += 11 * Math.pow(base, (i * -1) - 1);
                    break;
                case 'C':
                case 'c':
                    convertedFloat += 12 * Math.pow(base, (i * -1) - 1);
                    break;
                case 'D':
                case 'd':
                    convertedFloat += 13 * Math.pow(base, (i * -1) - 1);
                    break;
                case 'E':
                case 'e':
                    convertedFloat += 14 * Math.pow(base, (i * -1) - 1);
                    break;
                case 'F':
                case 'f':
                    convertedFloat += 15 * Math.pow(base, (i * -1) - 1);
                    break;
                default:
                    convertedFloat += (Integer.parseInt("" + floatNumber.charAt(i))) * Math.pow(base, (i * -1) - 1);
                    break;
            }
        return convertedFloat;
    }

    public String toDecimal(String num, int base)/*Convert any thing to Decimal*/ {
        if (!checkInput(num, base))
            return error /*"ERROR"*/;
        String intNumber;
        String floatNumber;

        long convertedInt;
        double convertedFloat = 0;
//////////////////////// split into integer and float //////////////////////////////
        if (num.lastIndexOf(dot) == -1) {
            intNumber = num;
            floatNumber = "";
        } else {
            intNumber = num.substring(0, num.lastIndexOf(dot));
            floatNumber = num.substring(num.lastIndexOf(dot) + 1, num.length());
        }
//////////////////////////Convert our base to decimal//////////////////////////////
        convertedInt = Long.valueOf(intNumber, base);

        if (base > 10)
            convertedFloat = hexToDecimal(floatNumber, base);
        else
            for (int i = 0; i < floatNumber.length(); i++)
                convertedFloat += (Integer.parseInt("" + floatNumber.charAt(i))) * Math.pow(base, (i * -1) - 1);

        return round(Double.toString(convertedInt + convertedFloat));
    }

    private String decimalToHex(double remainderFloat, int base)/*Convert floating Decimal to Hexadecimal*/ {
        String floatNumber = "";

        while (remainderFloat > 0) {
            int remainderInt;
            remainderInt = (int) (remainderFloat * base);
            remainderFloat = (remainderFloat * base) - remainderInt;
            switch (remainderInt) {
                case 10:
                    floatNumber += 'A';
                    break;
                case 11:
                    floatNumber += 'B';
                    break;
                case 12:
                    floatNumber += 'C';
                    break;
                case 13:
                    floatNumber += 'D';
                    break;
                case 14:
                    floatNumber += 'E';
                    break;
                case 15:
                    floatNumber += 'F';
                    break;
                default:
                    floatNumber += remainderInt;
                    break;
            }
        }
        return floatNumber;
    }

    public String decimalTo(String num, int base)/*Convert decimal to any thing*/ {
        if (!checkInput(num, 10))
            return error /*"ERROR"*/;
        if (base == 10)
            return num;

        String intNumber;
        String floatNumber;
//////////////////////// split into integer and float //////////////////////////////
        if (num.lastIndexOf(dot) == -1) {
            intNumber = num;
            floatNumber = "";
        } else {
            intNumber = num.substring(0, num.lastIndexOf(dot));
            floatNumber = num.substring(num.lastIndexOf(dot) + 1, num.length());
        }
//////////////////////////Convert decimal to our new base/////////////////////////////

        intNumber = Long.toString(intNumber.equals("") ? 0L : Long.parseLong(intNumber), base);

        double remainderFloat = 0;
        if (!floatNumber.equals(""))
            remainderFloat = Double.parseDouble("0." + floatNumber);
        floatNumber = "";
        if (base > 10)
            floatNumber = decimalToHex(remainderFloat, base);
        else
            while (remainderFloat > 0) {
                int remainderInt;
                remainderInt = (int) (remainderFloat * base);
                remainderFloat = (remainderFloat * base) - remainderInt;
                floatNumber += remainderInt;
            }

        floatNumber = (floatNumber.equals("")) ? Character.toString(zero) : floatNumber;

        return round(toUpper(intNumber + dot + floatNumber));
    }

    public String fromTo(String num, int fromBase, int toBase)/*Convert from base to another base*/ {
        if (!checkInput(num, fromBase))
            return error /*"ERROR"*/;
        if (fromBase == toBase)
            return num;

        return decimalTo(format(toDecimal(num, fromBase)), toBase);
    }

    private boolean checkInput(String input, int max)/*Check if input valid to it's base*/ {
        if (max > 10)
            return true;
        char chars[] = input.toCharArray();
        int values[] = new int[input.length()];
        for (int i = 0; i < input.length(); i++)
            switch (chars[i]) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                    return false;
                case '.':
                case ',':
                    values[i] = 0;
                    break;
                default:
                    values[i] = Integer.parseInt(Character.toString(chars[i]));
                    break;
            }
        for (int i = 0; i < input.length(); i++) {
            if (chars[i] == dot )
                continue;
            if (values[i] >= max)
                return false;
        }
        return true;
    }

    private String toUpper(String hex)/*Convert string to upper case*/ {
        char[] chars = hex.toCharArray();
        for (int i = 0; i < hex.length(); i++)
            if (Character.isLetter(chars[i]))
                chars[i] = Character.toUpperCase(chars[i]);
        return new String(chars);
    }

    private String round(String string)/*Round the result*/ {
        char chars[] = string.toCharArray();

        if(string.contains("E"))
            return string;

        int i = string.lastIndexOf(dot) + 8 > string.length() - 1 ? string.length() - 1 : string.lastIndexOf(dot) + 8;
        for (; i >= 0; i--) {
            if (chars[i] == dot)
                return String.copyValueOf(chars, 0, i);
            if (chars[i] != zero)
                return String.copyValueOf(chars, 0, i + 1);
        }
        return string;
    }

    private String format (String string){
        return BigDecimal.valueOf(Double.valueOf(string)).toPlainString();
    }
}