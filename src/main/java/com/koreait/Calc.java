package com.koreait;

import java.util.Arrays;

public class Calc {
    public static int run(String exp) {

        exp = exp.replace("- ", "+ -");


        String[] bits = exp.split(" \\+ ");

        int a = 0;
        String b;
        for (int i = 0; i < bits.length; i++) {
            b = bits[i];
            a = Integer.parseInt(b);
            a++;
        }
        return a;
//        throw new RuntimeException("해석불가 : 올바른 식이 필요해.");
    }
}