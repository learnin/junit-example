package example;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * CodeIQ 現在時刻が関わるテスト
 * https://codeiq.jp/magazine/2013/11/1475/
 * <p/>
 * 対象クラスのテスト用サブクラスをテスト内で作成(Test-Specific Subclass)アプローチのパラメタライズドテストの例
 */
@RunWith(Parameterized.class)
public class GreeterParameterizedTest {

    private static AbstractGreeter createGreeterWithHourMinuteSecond(final int hour, final int minute, final int second) {
        return new JapaneseGreeter() {
            @Override
            public Date getNow() {
                return new DateTime(2014, 1, 1, hour, minute, second).toDate();
            }
        };
    }

    static class Fixture {
        int hour;
        int minute;
        int second;

        Fixture(int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        @Override
        public String toString() {
            return hour + ":" + minute + ":" + second;
        }
    }

    private final Fixture params;
    private final String expected;

    public GreeterParameterizedTest(Fixture fixture, String expected) {
        this.params = fixture;
        this.expected = expected;
    }

    @Parameters(name = "index={index}: {0} なら {1}")
    public static Iterable<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {new Fixture(5, 0, 0), "おはようございます"},
                {new Fixture(11, 59, 59), "おはようございます"},
                {new Fixture(12, 0, 0), "こんにちは"},
                {new Fixture(17, 59, 59), "こんにちは"},
                {new Fixture(18, 0, 0), "こんばんは"},
                {new Fixture(23, 59, 59), "こんばんは"},
                {new Fixture(0, 0, 0), "こんばんは"},
                {new Fixture(4, 59, 59), "こんばんは"}
        });
    }

    @Test
    public void 朝_昼_夜の挨拶が時刻通りか() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(params.hour, params.minute, params.second);
        assertThat(describeExpectation(), sut.greet(), is(expected));
    }

    private String describeExpectation() {
        return params.hour + ":" + params.minute + ":" + params.second + "の挨拶は「" + expected + "」であるべき";
    }

}
