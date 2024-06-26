package com.koreait;

import java.util.Arrays;
import java.util.List;

public class Calc {
    public static int run(String exp) {
        exp = exp.trim();
        exp = stripOuterBrackets(exp);
        // 단일항이 들어오면 바로 리턴
        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToSum = exp.contains(" + ") || exp.contains(" - ");
        boolean needToSplit = exp.contains("(") || exp.contains(")");
        boolean needToCompound = needToSum && needToMulti;

//        if (exp.contains("(")) { //괄호가 있다면 실행.
//            int openBracket = exp.indexOf("("); // 괄호 위치 인덱스 번호로 찾기.
//            int closeBracket = exp.indexOf(")");
//            int count = 0; //괄호 개수 세기
//            for (int i = openBracket; i < exp.length(); i++) { //인덱스 길이만큼 반복하면서 괄호 찾기
//                if (exp.charAt(i) == '(') count++; //괄호 '(' 추적후 세기.
//                else if (exp.charAt(i) == ')') count--; // ')'가 시작되면 카운트 감소
//                if (count == 0) { // '(' 와 ')'의 개수가 같아야 식이 성립하기 때문에 0이될 때를 조건으로 삼음.
//                    closeBracket = i; // 반복문 반복 횟수가 괄호 마지막 인덱스이기 때문에 그 값을 closeBraket에 넣어줌.
//                    break; //브레이크
//                }
//            }
//            String expression = exp.substring(openBracket + 1, closeBracket); //첫괄호와 끝괄호 위치를 걷어냄.
//            int expResult = run(expression); // run메서드 전달 실행.
//            exp = exp.substring(0, openBracket) + expResult + exp.substring(closeBracket + 1);
//            //여는 괄호 이전의 수식 + 괄호 수식 결과값 + 닫는 괄호 이후의 수식
//            return run(exp); // run메서드 전달 실행.
//        }

        // 강사님 코드
        if (needToSplit) {
            int splitPointIndex = findSplitPointIndex(exp);

            String firstExp = exp.substring(0, splitPointIndex);
            String secondExp = exp.substring(splitPointIndex + 1);

            char operator = exp.charAt(splitPointIndex);

            exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);

            return Calc.run(exp);
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

    private static int findSplitPointIndex(String exp) {
        int index = findSplitPointIndexBy(exp, '+');

        if (index >= 0) return index;

        return findSplitPointIndexBy(exp, '*');
    }

    private static int findSplitPointIndexBy(String exp, char findChar) {
        int brackesCount = 0;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') {
                brackesCount++;
            } else if (c == ')') {
                brackesCount--;
            } else if (c == findChar) {
                if (brackesCount == 0) return i;
            }
        }
        return -1;
    }
    private static String stripOuterBrackets(String exp) {
//        while (exp.startsWith("(") && exp.endsWith(")")) {
//            int openCount = 0;
//            boolean canStrip = true;
//
//            for (int i = 0; i < exp.length(); i++) {
//                if (exp.charAt(i) == '(') openCount++;
//                if (exp.charAt(i) == ')') openCount--;
//
//
//                if (openCount == 0 && i != exp.length() - 1) {
//                    canStrip = false;
//                    break;
//                }
//            }
//
//            if (canStrip) {
//
//                exp = exp.substring(1, exp.length() - 1).trim();
//            } else {
//                break;
//            }
//        }
//        return exp;

        int outerBracketsCount = 0;

        while (exp.charAt(outerBracketsCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketsCount) == ')') {
            outerBracketsCount++;
        }

        if (outerBracketsCount == 0) return exp;

        return exp.substring(outerBracketsCount, exp.length() - outerBracketsCount);
    }

}