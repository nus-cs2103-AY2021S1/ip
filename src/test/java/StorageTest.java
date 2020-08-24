import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.DeadlineTask;
import task.Task;
import task.TodoTask;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StorageTest {

    @Test
    public void TestReadValidFile() throws DukeException {
        assertEquals(new Storage("data/duke.txt").readFile(), new File("data/duke.txt"));
    }

    @Test
    public void TestLoad() throws DukeException {
        List<Task> arrayList = new Storage("data/duke.txt").load();
        assertEquals(arrayList.size(), 1);
        assertEquals(arrayList.get(0), new TodoTask("Wash Clothes", 1));
    }

    // Test data/duke.txt if it doesn't exist in test/data/duke.txt
    @Test
    public void TestLoadEmptyDirectory() throws DukeException {
        assertEquals(new Storage("data/dukeEmpty.txt").load(), new ArrayList<>());
    }


    @Test
    public void TestReadLine() throws DukeException {
        assertEquals(new Storage("").readLine("D | 0 | tasking | 2020-02-02T18:00"),
                    new DeadlineTask("tasking", LocalDateTime.of(2020,2,2,18,0)));
    }

    @Test
    public void TestSaveTask() throws DukeException {
        Storage testStorage = new Storage("data/saveTask.txt");
        testStorage.load();
        TaskList taskList = new TaskList();

        taskList.addTask(new TodoTask("Wash Clothes", 1));
        taskList.addTask(new TodoTask("Do tutorial", 0));

        testStorage.saveTasks(taskList);

        List<Task> arrayList = testStorage.load();
        assertEquals(arrayList.size(), 2);
        assertEquals(arrayList.get(0), new TodoTask("Wash Clothes", 1));
        assertEquals(arrayList.get(1), new TodoTask("Do tutorial", 0));


    }

}
