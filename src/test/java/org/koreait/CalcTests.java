package org.koreait;

import com.koreait.Calc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class CalcTests {
    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 == 2")
    void t2() {
        assertThat(Calc.run("2 + 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 == 2")
    void t3() {
        assertThat(Calc.run("2 + 2")).isEqualTo(4);
    }

    @Test
    @DisplayName("50 - 30 == 20")
    void t4() {
        assertThat(Calc.run("50 - 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("100 - 30 == 70")
    void t5() {
        assertThat(Calc.run("100 - 30")).isEqualTo(70);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t6() {
        assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t7() {
        assertThat(Calc.run("10 - 20 + 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void t8() {
        assertThat(Calc.run("10 - 10 - 10 - 10 + 10 + 10 - 10")).isEqualTo(-10);
    }

    @Test
    @DisplayName("10 * 10 == 100")
    void t9() {
        assertThat(Calc.run("10 * 10")).isEqualTo(100);
    }

    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void t10() {
        assertThat(Calc.run("10 * 10 * 10")).isEqualTo(1000);
    }

    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void t11() {
        assertThat(Calc.run("10 + 5 * 2")).isEqualTo(20);
    }

    @Test
    @DisplayName("20 + 10 * 2 == 40")
    void t12() {
        assertThat(Calc.run("20 + 10 * 2")).isEqualTo(40);
    }

    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void t13() {
        assertThat(Calc.run("20 + 10 + 5 * 2")).isEqualTo(40);
    }

    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void t14() {
        assertThat(Calc.run("10 * 20 + 10 + 5 * 2")).isEqualTo(220);
    }

    @Test
    @DisplayName("(10 + 20) == 30")
    void t15() {
        assertThat(Calc.run("(10 + 20)")).isEqualTo(30);
    }
}
