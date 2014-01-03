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
        DateTime dt = new DateTime(getNow());
        int nowHour = dt.hourOfDay().get();
        if (nowHour >= 5 && nowHour < 12) {
            return goodMorning();
        } else if (nowHour >= 12 && nowHour < 18) {
            return goodAfternoon();
        } else {
            return goodEvening();
        }
    }

    protected abstract String goodEvening();

    protected abstract String goodAfternoon();

    protected abstract String goodMorning();

    protected Date getNow() {
        return new Date();
    }
}
