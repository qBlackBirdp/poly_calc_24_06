package com.koreait;

import java.util.Arrays;
import java.util.List;

public class Calc {
    public static int run(String exp) {

        boolean needToMulti = exp.contains("*");
        boolean needToSum = exp.contains("+");
        boolean needToCompound = needToSum && needToMulti;
//        boolean needToSub = exp.contains("-");

        int sum = 0;
//        int sub = 0;
        int mult = 1;
        if(needToCompound) {
            String[] bits = exp.split(" \\+ ");

            return Integer.parseInt(bits[0]) + run(bits[1]);
        }
        else if(needToSum) {
            exp = exp.replace("- ", "+ -");

            String[] bits = exp.split(" \\+ ");

            for (String bit : bits) {
                sum += Integer.parseInt(bit);
            }

            return sum; // 리턴값 rs.

        }
//        else if(needToSub) {
//
//            String[] bits = exp.split(" \\- ");
//
//            for (String bit : bits) {
//                sub -= Integer.parseInt(bit);
//            }
//            return sub;
//        }
        else if(needToMulti){
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