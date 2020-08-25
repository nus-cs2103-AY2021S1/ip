package main.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BobEventTest {
    @Test
    public void getPeriod_period_success() {
        Event event = new Event("meet friends","2020-05-18 2000 to 2020-05-18 2100");
        Assertions.assertEquals("May 18 2020 2000 to May 18 2020 2100", event.getPeriod());
    }

    @Test
    public void saveFormat_savedData_success() {
        Event event = new Event("meet friends","2020-05-18 2000 to 2020-05-18 2100");
        Assertions.assertEquals("E | 0 | meet friends | 2020-05-18 2000 to 2020-05-18 2100", event.saveFormat());
    }

    @Test
    public void toString_eventString_success() {
        Event event = new Event("meet friends","2020-05-18 2000 to 2020-05-18 2100");
        Assertions.assertEquals("[✘] meet friends (at: May 18 2020 2000 to May 18 2020 2100)", event.toString());
    }

}
