package duke;

import duke.tool.Storage;
import duke.tool.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadDataTest {
    @Test
    public void LoadDataTest() throws IOException {
        Storage storage = new Storage("data/tasks.txt");
        TaskList l = new TaskList(storage.loadData());
        assertEquals(l.getTask(0).toString(), 1);

    }
}