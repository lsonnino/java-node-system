package jns.example;

import jns.type.BooleanData;
import jns.type.NumberData;

public class Type {
    public static void main(String[] args) {
        BooleanData bool = new BooleanData(true);
        NumberData integerData = new NumberData();
        System.out.println(integerData.convert(bool));
    }
}
