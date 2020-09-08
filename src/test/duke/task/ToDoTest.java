package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString_doneToDo() {
        assertEquals("[T][\u2713] Submit homework",
                new ToDo("Submit homework", true).toString());
    }

    @Test
    void testToString_undoneToDo() {
        assertEquals("[T][\u2718] Submit proposal",
                new ToDo("Submit proposal", false).toString());
    }
}