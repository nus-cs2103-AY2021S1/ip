package duke;

import duke.tasks.Deadline;
import duke.tasks.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void testSaveAndLoad() throws DukeException{
        Storage s = new Storage("saveTest.txt");
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("blah"));
        tasks.addTask(new Deadline("ded", "2002-02-20"));
        tasks.done(1);
        s.save(tasks);

        TaskList loadedTasks = s.load();
        assertEquals(tasks, loadedTasks);
    }
}
