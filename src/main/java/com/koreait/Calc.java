package com.koreait;

import java.util.Arrays;
import java.util.List;

public class Calc {

    public static boolean debug = true;
    public static int runCallCount = 0;


    public static int run(String exp) {
        runCallCount++;

        exp = exp.trim();
        exp = stripOuterBrackets(exp);


        // 만약에 -( 패턴이라면, 내가 갖고있는 코드는 해석할 수 없으므로 해석할 수 있는 형태로 수정
        int[] pos = null;
        while ((pos = findCaseMinusBracket(exp)) != null) {
            exp = changeMinusBracket(exp, pos[0], pos[1]);
        }


        if (debug) {
            System.out.printf("exp(%d) : %s\n", runCallCount, exp);
        }

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
            exp = exp.replace("- ", "+ -");
            String[] bits = exp.split(" \\+ ");

            for (int i = 0; i < bits.length; i++) {
            }
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

    private static String changeMinusBracket(String exp, int startPos, int endPos) {
        String head = exp.substring(0, startPos);
        String body = "(" + exp.substring(startPos + 1, endPos + 1) + " * -1)";
        String tail = exp.substring(endPos + 1);

        exp = head + body + tail;

        return exp;
    }

    private static int[] findCaseMinusBracket(String exp) {
        for (int i = 0; i < exp.length() - 1; i++) {
            if (exp.charAt(i) == '-' && exp.charAt(i + 1) == '(') {
                // 발견

                int bracketsCount = 1;

                for (int j = i + 2; j < exp.length(); j++) {
                    char c = exp.charAt(j);

                    if (c == '(') {
                        bracketsCount++;
                    } else if (c == ')') {
                        bracketsCount--;
                    }

                    if (bracketsCount == 0) {
                        return new int[]{i, j};
                    }
                }
            }
        }

        return null;
    }

    private static int findSplitPointIndex(String exp) {

        int index = findSplitPointIndexBy(exp, '+');

        if (index >= 0) return index;

        return findSplitPointIndexBy(exp, '*');
    }

    private static int findSplitPointIndexBy(String exp, char findChar) {

        int bracketsCount = 0;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') {
                bracketsCount++;
            } else if (c == ')') {
                bracketsCount--;
            } else if (c == findChar) {
                if (bracketsCount == 0) return i;
            }
        }
        return -1;
    }

    private static String stripOuterBrackets(String exp) {

        if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
            int bracketsCount = 0;

            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') {
                    bracketsCount++;
                } else if (exp.charAt(i) == ')') {
                    bracketsCount--;
                }

                if (bracketsCount == 0) {
                    if (exp.length() == i + 1) {
                        return stripOuterBrackets(exp.substring(1, exp.length() - 1));
                    }

                    return exp;
                }
            }
        }
        return exp;
    }
}