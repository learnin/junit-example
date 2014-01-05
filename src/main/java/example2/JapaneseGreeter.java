package example2;

/**
 * CodeIQ 現在時刻が関わるテスト
 * https://codeiq.jp/magazine/2013/11/1475/
 *
 * 現在時刻へのアクセスを行うインターフェイスを抽出するアプローチの例
 */
public class JapaneseGreeter extends AbstractGreeter {

    @Override
    protected String goodMorning() {
        return "おはようございます";
    }

    @Override
    protected String goodAfternoon() {
        return "こんにちは";
    }

    @Override
    protected String goodEvening() {
        return "こんばんは";
    }

}
