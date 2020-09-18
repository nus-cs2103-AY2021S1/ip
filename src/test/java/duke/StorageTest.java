package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Todo;

public class StorageTest {
    @Test
    public void testSaveAndLoad() throws DukeException {
        Storage s = new Storage("storageTaskTest.txt", "storageNoteTest.txt");
        TaskList tasks = new TaskList();
        tasks.add(new Todo("buy a gift"));
        tasks.add(new Deadline("ask her out", LocalDateTime.parse("2020-10-08T21:00")));
        tasks.get(1).markDone();
        s.writeFile(tasks);

        TaskList loadedTasks = new TaskList(s.load(), s.loadNotes());
        assertEquals(tasks, loadedTasks);
    }
}
