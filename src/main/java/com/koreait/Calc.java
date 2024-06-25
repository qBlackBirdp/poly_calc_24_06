package com.koreait;

import java.util.Arrays;
import java.util.List;

public class Calc {
    public static int run(String exp) {
        exp = stripOuterBrackets(exp);
        // 단일항이 들어오면 바로 리턴
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }
        boolean needToMulti = exp.contains(" * ");
        boolean needToSum = exp.contains(" + ")||exp.contains(" - ");
        boolean needToCompound = needToSum && needToMulti;


        int sum = 0;
        int mult = 1;
        int rs = 0;


        if (needToCompound) {
            String[] bits = exp.split(" \\+ ");


            char multi = '*';


            for (int i = 0; i < bits.length; i++) {
                if (bits[i].indexOf(multi) != -1) {
                    System.out.println(bits[i]);
                    rs += run(bits[i]);
                }
            }
            for (int i = 0; i < bits.length; i++) {
                if (bits[i].indexOf(multi) == -1) {
                    System.out.println(bits[i]);
                    sum += Integer.parseInt(bits[i]);
                }
            }
            return sum + rs;
        } else if (needToSum) {

            exp = exp.replace("- ", "+ -");

            String[] bits = exp.split(" \\+ ");

            for (String bit : bits) {
                sum += Integer.parseInt(bit);
            }

            return sum;

        } else if (needToMulti) {
            String[] bits = exp.split(" \\* ");
            for (String bit : bits) {

                mult *= Integer.parseInt(bit);

            }
            return mult;
        }

        return sum + mult;
//        throw new RuntimeException("해석불가 : 올바른 식이 필요해.");
    }

    private static String stripOuterBrackets(String exp) {
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
                exp = exp.substring(1, exp.length() - 1);
            }
        }
        return exp;
    }
}
