package com.duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void reformatDateTest() {
        String res = "(by: Sunday)";
        String input = "by Sunday";
        assertEquals(res, Task.reformatDate(input));
    }
}
