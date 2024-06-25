package com.koreait;

import java.util.Arrays;

public class Calc {
    public static int run(String exp) {

        exp = exp.replace("- ", "+ -");


        String[] bits = exp.split(" \\+ "); //이까지는 수업시간에 도저히 풀지 못하던 구간이였다.

        int rs = 0; //합을 저장할 변수 선언.

        for (String bit : bits) { //bits 배열을 순회
            rs += Integer.parseInt(bit); // 순회하면서 값들을 int로 전환 시키며 더함.
        }

        return rs; // 리턴값 rs.
//        throw new RuntimeException("해석불가 : 올바른 식이 필요해.");
    }
}