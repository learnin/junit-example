package example2;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class GreetRuleTest {

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

    @RunWith(Parameterized.class)
    public static class 朝の挨拶時間帯 {

        private final Fixture params;
        private final boolean expected;

        public 朝の挨拶時間帯(Fixture fixture, boolean expected) {
            this.params = fixture;
            this.expected = expected;
        }

        @Parameters(name = "index={index}: {0} なら朝である は {1}")
        public static Iterable<Object[]> getParameters() {
            return Arrays.asList(new Object[][]{
                    {new Fixture(5, 0, 0), true},
                    {new Fixture(11, 59, 59), true},
                    {new Fixture(12, 0, 0), false},
                    {new Fixture(17, 59, 59), false},
                    {new Fixture(18, 0, 0), false},
                    {new Fixture(23, 59, 59), false},
                    {new Fixture(0, 0, 0), false},
                    {new Fixture(4, 59, 59), false}
            });
        }

        @Test
        public void isMorningは5時から11時59分59秒まではtrueを返し_それ以外はfalseを返す() throws Exception {
            DefaultGreetRule sut = new DefaultGreetRule();
            Date date = new DateTime(2014, 1, 1, params.hour, params.minute, params.second).toDate();
            assertThat(sut.isMorning(date), is(expected));
        }
    }

    @RunWith(Parameterized.class)
    public static class 昼の挨拶時間帯 {

        private final Fixture params;
        private final boolean expected;

        public 昼の挨拶時間帯(Fixture fixture, boolean expected) {
            this.params = fixture;
            this.expected = expected;
        }

        @Parameters(name = "index={index}: {0} なら昼である は {1}")
        public static Iterable<Object[]> getParameters() {
            return Arrays.asList(new Object[][]{
                    {new Fixture(5, 0, 0), false},
                    {new Fixture(11, 59, 59), false},
                    {new Fixture(12, 0, 0), true},
                    {new Fixture(17, 59, 59), true},
                    {new Fixture(18, 0, 0), false},
                    {new Fixture(23, 59, 59), false},
                    {new Fixture(0, 0, 0), false},
                    {new Fixture(4, 59, 59), false}
            });
        }

        @Test
        public void isAfternoonは12時から17時59分59秒まではtrueを返し_それ以外はfalseを返す() throws Exception {
            DefaultGreetRule sut = new DefaultGreetRule();
            Date date = new DateTime(2014, 1, 1, params.hour, params.minute, params.second).toDate();
            assertThat(sut.isAfternoon(date), is(expected));
        }
    }

    @RunWith(Parameterized.class)
    public static class 夜の挨拶時間帯 {

        private final Fixture params;
        private final boolean expected;

        public 夜の挨拶時間帯(Fixture fixture, boolean expected) {
            this.params = fixture;
            this.expected = expected;
        }

        @Parameters(name = "index={index}: {0} なら夜である は {1}")
        public static Iterable<Object[]> getParameters() {
            return Arrays.asList(new Object[][]{
                    {new Fixture(5, 0, 0), false},
                    {new Fixture(11, 59, 59), false},
                    {new Fixture(12, 0, 0), false},
                    {new Fixture(17, 59, 59), false},
                    {new Fixture(18, 0, 0), true},
                    {new Fixture(23, 59, 59), true},
                    {new Fixture(0, 0, 0), true},
                    {new Fixture(4, 59, 59), true}
            });
        }

        @Test
        public void isEveningは18時から4時59分59秒まではtrueを返し_それ以外はfalseを返す() throws Exception {
            DefaultGreetRule sut = new DefaultGreetRule();
            Date date = new DateTime(2014, 1, 1, params.hour, params.minute, params.second).toDate();
            assertThat(sut.isEvening(date), is(expected));
        }
    }

}
