package com.duke.tasks;

import com.duke.tasks.Events;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void parseToSaveFormatTest() {
        Deadlines deadline = new Deadlines("Clear trash", "2020-06-06 800", true);
        String res = "D - 1 - Clear trash - 6/06/2020 800";
        assertEquals(res, deadline.parseToSaveFormat());
    }

    @Test
    public void toStringTest() {
        Deadlines deadline = new Deadlines("project meeting", "2020-08-06 2130", false);
        String res = "[D][✗] project meeting (by: 6 AUGUST 2020, 930pm)";
        assertEquals(res, deadline.toString());
    }
}