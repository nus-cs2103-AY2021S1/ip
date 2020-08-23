package com.duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {

    @Test
    public void parseToSaveFormatTest() {
        Events event = new Events("Clear trash", "2020-06-06 800", true);
        String res = "E - 1 - Clear trash - 6/06/2020 800";
        assertEquals(res, event.parseToSaveFormat());
    }

    @Test
    public void toStringTest() {
        Events event = new Events("project meeting", "2020-08-06 2130", false);
        String res = "[E][✗] project meeting (at: 6 AUGUST 2020, 930pm)";
        assertEquals(res, event.toString());
    }
}
