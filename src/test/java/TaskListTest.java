import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void runTaskListTest() {
        TaskList taskListTest = new TaskList();
        taskListTest.addTask(new Todo("todo"));
        taskListTest.addTask(new Deadline("deadline", "01-9-11 8:46"));
        assertEquals(taskListTest.size(), 2);
        taskListTest.get(1).markAsDone();
        taskListTest.removeTask(0);
        assertEquals(taskListTest.get(0).toString(), "[D][O] deadline (by: 2001-09-11T08:46)");
    }
}
