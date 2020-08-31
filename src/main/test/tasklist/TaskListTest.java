package tasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDoTask;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void init() {
        this.taskList = new TaskList(Collections.singletonList(new ToDoTask("hi")));
    }

    @Test
    void mark_test_done_success() {
        Task itemOnList = this.taskList.getList().get(0);
        assertFalse(itemOnList.getIsDone());
        this.taskList.markDone(0);
        assertTrue(itemOnList.getIsDone());
    }
}
