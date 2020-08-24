package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void dataStorage_displaysCorrectStorageName_displayZeroForUndone() {
        ToDo testToDoUndone = new ToDo("Testing");
        assertEquals("T | 0 | Testing", testToDoUndone.dataStorage());
    }

    @Test
    public void dataStorage_displaysCorrectStorageNameForDone_displayOneForDone() {
        ToDo testToDoUndone = new ToDo("Testing");
        testToDoUndone.markDone();
        assertEquals("T | 1 | Testing", testToDoUndone.dataStorage());
    }
}
