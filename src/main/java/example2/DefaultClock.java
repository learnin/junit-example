package example2;

import java.util.Date;

public class DefaultClock implements Clock {

    public Date getNow() {
        return new Date();
    }

}
