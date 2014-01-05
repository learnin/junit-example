package example;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * CodeIQ 現在時刻が関わるテスト
 * https://codeiq.jp/magazine/2013/11/1475/
 *
 * 対象クラスのテスト用サブクラスをテスト内で作成(Test-Specific Subclass)アプローチの例
 */
public abstract class AbstractGreeter {

    public String greet() {
        Date now = getNow();
        if (isMorning(now)) {
            return goodMorning();
        }
        if (isAfternoon(now)) {
            return goodAfternoon();
        }
        return goodEvening();
    }

    protected abstract String goodMorning();

    protected abstract String goodAfternoon();

    protected abstract String goodEvening();

    private boolean isMorning(Date date) {
        DateTime dt = new DateTime(date);
        int nowHour = dt.hourOfDay().get();
        return nowHour >= 5 && nowHour < 12;
    }

    private boolean isAfternoon(Date date) {
        DateTime dt = new DateTime(date);
        int nowHour = dt.hourOfDay().get();
        return nowHour >= 12 && nowHour < 18;
    }

    protected Date getNow() {
        return new Date();
    }
}
