package duke.storage;

import duke.task.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void textToTaskTest() {
        String fileText = "T | 1 | do something";

        Task expected = new Task("todo do something");
        expected.markAsDone();

        Task actual = Storage.textToTask(fileText);

        assertEquals(expected, actual);
    }

}
