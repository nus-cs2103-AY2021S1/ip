package logic;

import duke.logic.StorageManager;
import duke.task.DukeTask;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageManagerTest {
    @Test
    public void testLoadData() throws FileNotFoundException {
        String path = "src/test/java/data/input.txt";
        StorageManager manager = new StorageManager(path);
        ArrayList<DukeTask> tasks = new ArrayList<DukeTask>();
        tasks.add(new TodoTask("test1"));
        tasks.add(new TodoTask("test2"));

        assertEquals(tasks, manager.loadData());
    }

    @Test
    public void testSaveData() throws IOException {
        String path = "src/test/java/data/output.txt";
        StorageManager manager = new StorageManager(path);
        ArrayList<DukeTask> tasks = new ArrayList<DukeTask>();
        tasks.add(new TodoTask("test1"));
        tasks.add(new TodoTask("test2"));
        tasks.add(new TodoTask("test3"));

        manager.saveData(tasks);
    }
}
