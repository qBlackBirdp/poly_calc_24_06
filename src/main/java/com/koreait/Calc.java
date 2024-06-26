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

//        if (exp.contains("(")) {
//            int openBracket = exp.indexOf("(");
//            int closeBracket = exp.indexOf(")");
//            String expression = exp.substring(openBracket + 1, closeBracket);
//            int expResult = run(expression);
//            exp = exp.substring(0, openBracket) + expResult + exp.substring(closeBracket + 1);
//            return run(exp);
//        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToSum = exp.contains(" + ") || exp.contains(" - ");
        boolean needToSplit = exp.contains("(") || exp.contains(")");
        boolean needToCompound = needToSum && needToMulti;

        if (needToSplit) {
            int brackersCount = 0;
            int splitPointIndex = -1;

            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') brackersCount++;
                else if (exp.charAt(i) == ')') brackersCount--;
                if (brackersCount == 0) {
                    splitPointIndex = i;
                    break;
                }
            }
            String firstExp = exp.substring(0, splitPointIndex + 1);
            String secondExp = exp.substring(splitPointIndex + 4);

            return Calc.run(firstExp) + Calc.run(secondExp);
        }


        int sum = 0;
        int mult = 1;
        int rs = 0;


        if (needToCompound) {
            String[] bits = exp.split(" \\+ ");


            char multi = '*';


            for (int i = 0; i < bits.length; i++) {
                if (bits[i].indexOf(multi) != -1) {

                    rs += run(bits[i]);
                }
            }
            for (int i = 0; i < bits.length; i++) {
                if (bits[i].indexOf(multi) == -1) {

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
        int outerBracketsCount = 0;

        while (exp.charAt(outerBracketsCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketsCount) == ')') {
            outerBracketsCount++;
        }

        if (outerBracketsCount == 0) return exp;

        return exp.substring(outerBracketsCount, exp.length() - outerBracketsCount);
    }

}