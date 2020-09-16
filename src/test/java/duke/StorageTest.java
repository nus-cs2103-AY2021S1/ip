package duke;

import duke.task.Deadline;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void testSaveAndLoad() throws DukeException {
        Storage s = new Storage("storageTest.txt");
        TaskList tasks = new TaskList();
        tasks.add(new Todo("buy a gift"));
        tasks.add(new Deadline("ask her out", LocalDateTime.parse("2020-10-08T21:00")));
        tasks.get(1).markDone();
        s.writeFile(tasks);

        TaskList loadedTasks = new TaskList(s.load());
        assertEquals(tasks, loadedTasks);
    }
}
