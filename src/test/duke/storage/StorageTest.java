package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    Storage storage1 = new Storage("./src/test/tasks.txt");
    Storage storage2 = new Storage("./tasks.txt");

    @Test
    void load() {
        try {
            ArrayList<Task> list = storage1.load();
            Task task = list.get(0);
            assertTrue(list.size() == 1);
            assertTrue(task.getDescription().equals("what"));
            assertEquals(task.getType(), TaskType.DEADLINE);
            assertEquals(task.getStatus(), false);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void load_nonExistingFile() {
        try {
            storage2.load();
            fail();
        } catch (DukeException e) {
            assertTrue(e.getMessage().equals("\tFile not Found."));
        }
    }

    @Test
    void write() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Task("deadline", "what", LocalDate.parse("2019-01-01")));
        try {
            storage1.write(list);
        } catch (DukeException e) {
            fail();
        }
    }
}
