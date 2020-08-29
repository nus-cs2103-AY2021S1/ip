package com.duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void reformatDateTest() {
        String res = "(by: Sunday)";
        String input = "by Sunday";
        assertEquals(res, Task.reformatDate(input));
    }
}
