package logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.logic.StorageManager;
import duke.task.DukeTask;
import duke.task.TodoTask;


public class StorageManagerTest {
    @Test
    public void testLoadData() throws FileNotFoundException {
        String path = "src/test/java/data/input.txt";
        StorageManager manager = new StorageManager(path);
        ArrayList<DukeTask> tasks = new ArrayList<>();
        tasks.add(new TodoTask("test1"));
        tasks.add(new TodoTask("test2"));

        assertEquals(tasks, manager.loadData());
    }

    @Test
    public void testSaveData() throws IOException {
        String path = "src/test/java/data/output.txt";
        StorageManager manager = new StorageManager(path);
        ArrayList<DukeTask> tasks = new ArrayList<>();
        tasks.add(new TodoTask("test1"));
        tasks.add(new TodoTask("test2"));
        tasks.add(new TodoTask("test3"));

        manager.saveData(tasks);
    }
}
