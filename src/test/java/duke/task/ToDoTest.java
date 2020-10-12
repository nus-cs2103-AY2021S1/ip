package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void dataStorage_displaysCorrectStorageName_displayZeroForUndone() {
        ToDo testToDoUndone = new ToDo("Test for not done");
        assertEquals("T | 0 | Test for not done", testToDoUndone.formatToDo());
    }

    @Test
    public void dataStorage_displaysCorrectStorageNameForDone_displayOneForDone() {
        ToDo testToDoUndone = new ToDo("Test for done");
        testToDoUndone.markAsDone();
        assertEquals("T | 1 | Test for done", testToDoUndone.formatToDo());
    }

}