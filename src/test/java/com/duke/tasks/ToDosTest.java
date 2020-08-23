package com.duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void parseToSaveFormatTest() {
        ToDos todo = new ToDos("Clear trash", true);
        String res = "T - 1 - Clear trash";
        assertEquals(res, todo.parseToSaveFormat());
    }
}
