package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.Event;
import duke.task.ToDo;

class TaskListTest {

    private static final ToDo TODO = new ToDo("finish paperwork");
    private static final List<String> LIST_OF_STRING_TASKS = new ArrayList<>(List.of("[E]|0|Christmas party|my house",
            "[T]|1|clean my room"));

    @Test
    void getTask() {
        TaskList taskList = new TaskList(LIST_OF_STRING_TASKS);
        assertTrue(taskList.getTask(0) instanceof Event);
        assertTrue(taskList.getTask(1) instanceof ToDo);
    }

    @Test
    void getNumberOfTasks() {
        TaskList taskList = new TaskList(LIST_OF_STRING_TASKS);
        assertEquals(2, taskList.getNumberOfTasks());
    }

    @Test
    void isEmpty() {
        TaskList taskListEmpty = new TaskList();
        TaskList taskListNotEmpty = new TaskList(LIST_OF_STRING_TASKS);
        assertTrue(taskListEmpty.isEmpty());
        assertFalse(taskListNotEmpty.isEmpty());
    }
}
