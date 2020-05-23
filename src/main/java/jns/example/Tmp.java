package jns.example;

import jns.system.*;

public class Tmp {
    private enum Ex {
        ONE,
        TWO,
        THREE;
    }

    public static void main(String[] args) {
        Type type = new Type(Ex.ONE, SimpleType.class);
        java.lang.System.out.println(type.is(Ex.TWO));
    }
}
