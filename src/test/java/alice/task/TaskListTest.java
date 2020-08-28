package alice.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    void addTaskTest() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getNumberOfTasks());

        tasks.addTask(new TaskStub());
        assertEquals(1, tasks.getNumberOfTasks());
    }
}
