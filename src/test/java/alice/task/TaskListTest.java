package alice.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import alice.AliceException;
import alice.task.stub.TodoStub;

public class TaskListTest {
    @Test
    void addTaskTest() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getNumberOfTasks());

        tasks.addTask(new TodoStub());
        assertEquals(1, tasks.getNumberOfTasks());
    }

    @Test
    void loadSavedTasks() throws AliceException {
        List<String> encodedTasks = List.of("T | 1 | Task");
        TaskList tasks = new TaskList(encodedTasks);

        assertEquals(1, tasks.getNumberOfTasks());
        assertEquals("1. [T][✔] Task", tasks.getAllTasks());
    }
}
