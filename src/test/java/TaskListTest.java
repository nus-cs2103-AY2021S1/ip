import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import task.Task;

public class TaskListTest {
    @Test
    public void searchFor_validKeyword_success() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("book1", false);
        Task task2 = new Task("book2", false);
        Task task3 = new Task("computer", false);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        ArrayList<Task> result = new ArrayList<>();
        result.add(task1);
        result.add(task2);
        assertEquals(result, taskList.searchFor("book"));
    }
}
