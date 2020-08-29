package com.duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {

    @Test
    public void parseToSaveFormatTest() {
        ToDos todo = new ToDos("Clear trash", true);
        String res = "T - 1 - Clear trash";
        assertEquals(res, todo.parseToSaveFormat());
    }
}
