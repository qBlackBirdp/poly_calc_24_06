package com.koreait;

import java.util.Arrays;
import java.util.List;

public class Calc {
    public static int run(String exp) {

        boolean needToMulti = exp.contains("*");
        boolean needToSum = exp.contains("+");
        boolean needToCompound = needToSum && needToMulti;
        boolean needToSub = exp.contains("-");

        int sum = 0;
        int sub = 0;
        int mult = 1;
        int rs = 0;

        if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            char multi = '*';


            for (int i = 0; i < bits.length; i++) {
                if (bits[i].indexOf(multi) != -1) {
                    System.out.println(bits[i]);
                    rs = run(bits[i]);
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

            return sum; // 리턴값 rs.

        } else if (needToSub) {
            exp = exp.replace("- ", "+ -");

            String[] bits = exp.split(" \\+ ");

            for (String bit : bits) {
                sub += Integer.parseInt(bit);
            }
            return sub;
        } else if (needToMulti) {
            String[] bits = exp.split(" \\* ");
            for (String bit : bits) {

                mult *= Integer.parseInt(bit);

            }
            return mult;
        }
        if (mult == 1) {
            mult = 0;
        }
        return sum + mult + sub;
//        throw new RuntimeException("해석불가 : 올바른 식이 필요해.");
    }
}
