package tasks;

import org.junit.jupiter.api.Test;
import storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void getNumListTest() {
        Storage storage = new Storage("storage_test.txt");
        TaskList taskList = new TaskList(storage);
        Todo todo = new Todo("finish cs2103t work");
        taskList.addToList(todo);
        int num = taskList.getNumList();
        assertEquals(1, num);

    }

    @Test
    public void processTaskTest() {
        Deadline deadline = new Deadline("cs2101 meeting", "2020-08-27");
        String output = TaskList.processTasks(deadline);
        assertEquals("D | 0 | cs2101 meeting | 2020-08-27", output);
    }

}
