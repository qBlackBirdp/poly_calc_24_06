package com.koreait;

import java.util.Arrays;

public class Calc {
    public static int run(String exp) {

        boolean needToMulti = exp.contains("*");
        boolean needToSum = exp.contains("+");

        int sum = 0;
        int mult = 1;
        if(needToSum) {
            exp = exp.replace("- ", "+ -");


            String[] bits = exp.split(" \\+ ");

            bits = exp.split(" \\+ ");



            for (String bit : bits) {
                sum += Integer.parseInt(bit);
            }

            return sum; // 리턴값 rs.

        }else if(needToMulti){

            String[] bits = exp.split(" \\* ");



            for (String bit : bits) {

                mult *= Integer.parseInt(bit);

            }
            return mult;
        }
        return sum + mult;
//        throw new RuntimeException("해석불가 : 올바른 식이 필요해.");
    }
}

//int mult = Arrays.stream(bits).
//        mapToInt(Integer::parseInt).
//        reduce(1, (a, b) -> a * b);