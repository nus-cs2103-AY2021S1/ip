package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidIndexException;

public class TaskListTest {
    @Test
    public void getTaskSize_success() {
        TaskList taskList = new TaskList();
        String expected = "Now you have 0 tasks in the list.";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void deleteTask_invalidIndex_exception() {
        TaskList taskList = new TaskList();
        try {
            taskList.deleteTask(0);
        } catch (InvalidIndexException e) {
            assertEquals("☹ OOPS!!! There is no such task.", e.getMessage());
        }
    }
}
