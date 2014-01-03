package example;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * CodeIQ 現在時刻が関わるテスト
 * https://codeiq.jp/magazine/2013/11/1475/
 * <p/>
 * 対象クラスのテスト用サブクラスをテスト内で作成(Test-Specific Subclass)アプローチの例
 */
public class GreeterTest {

    private static AbstractGreeter createGreeterWithHourMinuteSecond(final int hour, final int minute, final int second) {
        return new JapaneseGreeter() {
            @Override
            public Date getNow() {
                return new DateTime(2014, 1, 1, hour, minute, second).toDate();
            }
        };
    }

    @Test
    public void 朝の挨拶は5時から() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(5, 0, 0);
        assertThat(sut.greet(), is("おはようございます"));
    }


    @Test
    public void 朝の挨拶は11時59分59秒まで() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(11, 59, 59);
        assertThat(sut.greet(), is("おはようございます"));
    }

    @Test
    public void 昼の挨拶は12時から() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(12, 0, 0);
        assertThat(sut.greet(), is("こんにちは"));
    }

    @Test
    public void 昼の挨拶は17時59分59秒まで() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(17, 59, 59);
        assertThat(sut.greet(), is("こんにちは"));
    }

    @Test
    public void 夜の挨拶は18時から() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(18, 0, 0);
        assertThat(sut.greet(), is("こんばんは"));
    }

    @Test
    public void _23時59分59秒も夜の挨拶() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(23, 59, 59);
        assertThat(sut.greet(), is("こんばんは"));
    }

    @Test
    public void _0時も夜の挨拶() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(0, 0, 0);
        assertThat(sut.greet(), is("こんばんは"));
    }

    @Test
    public void 夜の挨拶は4時59分59秒まで() throws Exception {
        AbstractGreeter sut = createGreeterWithHourMinuteSecond(4, 59, 59);
        assertThat(sut.greet(), is("こんばんは"));
    }

}
