package main.java.duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void toString_toDoWithDescription_suitableDescription() {
        assertEquals("[T][\u2718] a b c", new ToDo("a b c").toString());
    }

    @Test
    void record_toDoWithDescription_suitableDescription() {
        ToDo toDo = new ToDo("a b c");
        toDo.markAsCompleted();
        assertEquals("T | 1 | a b c", toDo.record());
    }
}