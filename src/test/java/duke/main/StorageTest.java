package duke.main;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void LoadEmptyTest() {
        Storage storage = new Storage("non-existent-path");
        TaskList taskList = storage.loadData(new UI());
        assertEquals(new ArrayList<Task>(), taskList.getTaskList());
    }
}
