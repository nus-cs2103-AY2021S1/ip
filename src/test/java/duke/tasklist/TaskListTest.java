package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskListTest {

    private TaskList task;

    @BeforeEach
    void init() {
        task = new TaskList();
        for (int i = 0; i < 3; i++) {
            task.add(new Task("hello world " + i));
        }
    }

    @Test
    public void size_checkSizeOfTheTaskList_success() {
        assertEquals(3, task.size());
    }

    @Test
    public void get_retrieveTaskFromTaskList_success() {
        assertEquals("[" + "\u2718" + "] hello world 2", task.get(2).toString());
    }

    @Test
    public void add_addTaskIntoTaskList_success() {
        // add again to test
        task.add(new Task("hello world 3"));
        assertEquals(4, task.size());
    }

    @Test
    public void remove_removeTaskFromTaskList_success() {
        task.remove(2);
        assertEquals(2, task.size());
    }
}
