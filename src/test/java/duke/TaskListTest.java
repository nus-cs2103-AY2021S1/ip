package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    void addDeadline_normalInput_deadlineAdded() {
        TaskList taskList = new TaskList();
        taskList.addDeadline("deadline_desc", false, LocalDate.of(2021, 12, 31));
        assertEquals(taskList.getNumOfTasks(), 1);
    }

    @Test
    void addEvent_normalInput_eventAdded() {
        TaskList taskList = new TaskList();
        taskList.addEvent("event_desc", false, LocalDate.of(2021, 12, 31));
        assertEquals(taskList.getNumOfTasks(), 1);
    }

    @Test
    void addTodo_normalInput_todoAdded() {
        TaskList taskList = new TaskList();
        taskList.addTodo("todo_desc", false);
        assertEquals(taskList.getNumOfTasks(), 1);
    }

    @Test
    void deleteAllTasks_noInput_allTasksDeleted() {
        TaskList taskList = new TaskList();
        taskList.addDeadline("deadline_desc", false, LocalDate.of(2021, 12, 31));
        taskList.addEvent("event_desc", false, LocalDate.of(2021, 12, 31));
        taskList.addTodo("todo_desc", false);
        taskList.deleteAllTasks();
        assertTrue(taskList.isEmpty());
    }

    @Test
    void deleteTaskAt_normalInput_taskDeleted() {
        TaskList taskList = new TaskList();
        taskList.addDeadline("deadline_desc", false, LocalDate.of(2021, 12, 31));
        taskList.addEvent("event_desc", false, LocalDate.of(2021, 12, 31));
        taskList.addTodo("todo_desc", false);
        taskList.deleteTaskAt(2);
        assertEquals(taskList.getNumOfTasks(), 2);
    }

    @Test
    void deleteTaskAt_invalidIndex_noTasksDeleted() {
        TaskList taskList = new TaskList();
        taskList.addDeadline("deadline_desc", false, LocalDate.of(2021, 12, 31));
        taskList.addEvent("event_desc", false, LocalDate.of(2021, 12, 31));
        taskList.addTodo("todo_desc", false);
        taskList.deleteTaskAt(5);
        assertEquals(taskList.getNumOfTasks(), 3);
    }
}
