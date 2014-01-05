package example2;

import java.util.Date;

/**
 * CodeIQ 現在時刻が関わるテスト
 * https://codeiq.jp/magazine/2013/11/1475/
 *
 * 現在時刻へのアクセスを行うインターフェイスを抽出するアプローチの例
 */
public abstract class AbstractGreeter {

    private Clock clock = new DefaultClock();

    private GreetRule greetRule = new DefaultGreetRule();

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void setGreetRule(GreetRule greetRule) {
        this.greetRule = greetRule;
    }

    public String greet() {
        Date now = clock.getNow();
        if (greetRule.isMorning(now)) {
            return goodMorning();
        }
        if (greetRule.isAfternoon(now)) {
            return goodAfternoon();
        }
        if (greetRule.isEvening(now)) {
            return goodEvening();
        }
        throw new UnsupportedOperationException();
    }

    protected abstract String goodMorning();

    protected abstract String goodAfternoon();

    protected abstract String goodEvening();

}
