package example2;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * CodeIQ 現在時刻が関わるテスト
 * https://codeiq.jp/magazine/2013/11/1475/
 *
 * 現在時刻へのアクセスを行うインターフェイスを抽出するアプローチの例
 */
public class JapaneseGreeterTest {

    private AbstractGreeter sut;
    private Clock clockStub;
    private final Date mockDate = new Date();

    @Before
    public void setUp() throws Exception {
        sut = new JapaneseGreeter();
        clockStub = mock(Clock.class);
        when(clockStub.getNow()).thenReturn(mockDate);
        sut.setClock(clockStub);
    }

    @Test
    public void 朝の挨拶時間帯なら朝の挨拶を返す() throws Exception {
        GreetRule mockRule = mock(GreetRule.class);
        when(mockRule.isMorning(any(Date.class))).thenReturn(true);
        sut.setGreetRule(mockRule);

        String result = sut.greet();

        assertThat(result, is("おはようございます"));
        verify(mockRule).isMorning(mockDate);
    }

    @Test
    public void 昼の挨拶時間帯なら昼の挨拶を返す() throws Exception {
        GreetRule mockRule = mock(GreetRule.class);
        when(mockRule.isAfternoon(any(Date.class))).thenReturn(true);
        sut.setGreetRule(mockRule);

        String result = sut.greet();

        assertThat(result, is("こんにちは"));
        verify(mockRule).isAfternoon(mockDate);
    }

    @Test
    public void 夜の挨拶時間帯なら夜の挨拶を返す() throws Exception {
        GreetRule mockRule = mock(GreetRule.class);
        when(mockRule.isEvening(any(Date.class))).thenReturn(true);
        sut.setGreetRule(mockRule);

        String result = sut.greet();

        assertThat(result, is("こんばんは"));
        verify(mockRule).isEvening(mockDate);
    }

}
