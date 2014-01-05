package example2;

import org.joda.time.DateTime;

import java.util.Date;

public class DefaultGreetRule implements GreetRule {

    public boolean isMorning(Date date) {
        DateTime dt = new DateTime(date);
        int nowHour = dt.hourOfDay().get();
        return nowHour >= 5 && nowHour < 12;
    }

    public boolean isAfternoon(Date date) {
        DateTime dt = new DateTime(date);
        int nowHour = dt.hourOfDay().get();
        return nowHour >= 12 && nowHour < 18;
    }

    public boolean isEvening(Date date) {
        return !isMorning(date) && !isAfternoon(date);
    }
}
