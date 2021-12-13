package duke.storage;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    Storage storage = new Storage();

    @Test
    public void deserializeTask_todo_success() {
        String input = "T | 0 | Run 5 km";
        Task output = storage.deserializeTask(input);

        assertEquals(output.getDescription(), "Run 5 km");
        assertEquals(output.getIsDone(), false);
    }

    @Test
    public void serializeTask_todo_success() {
        ToDo input = new ToDo("Write journal", true);
        String output = storage.serializeTask(input);

        assertEquals(output, "T | 1 | Write journal\n");
    }
}

