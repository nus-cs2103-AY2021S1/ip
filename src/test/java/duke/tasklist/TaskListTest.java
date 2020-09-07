package duke.tasklist;

import static duke.utils.Messages.MESSAGE_NO_SUCH_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.exceptions.NoSuchTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.utils.DukeDateTime;

public class TaskListTest {

    @Test
    public void testGetTasks() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        Event event = new Event("event", dateTime);
        Deadline deadline = new Deadline("deadline", dateTime);
        taskList.addTask(todo);
        taskList.addTask(event);
        taskList.addTask(deadline);
        ArrayList<Task> expected = new ArrayList<>(Arrays.asList(todo, event, deadline));
        assertIterableEquals(expected, taskList.getTasks());
    }

    @Test
    public void markTaskAsDone_validInput_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        taskList.addTask(todo);
        Task task = taskList.markTaskAsDone(0);
        assertEquals(todo, task);
    }

    @Test
    public void markTaskAsDone_outOfBounds_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            Task task = taskList.markTaskAsDone(0);
            fail();
        } catch (NoSuchTaskException e) {
            assertEquals(MESSAGE_NO_SUCH_TASK, e.getMessage());
        }
    }

    @Test
    public void deleteTask_validInput_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        taskList.addTask(todo);
        Task task = taskList.deleteTask(0);
        assertEquals(todo, task);
    }

    @Test
    public void deleteTask_outOfBounds_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            Task task = taskList.deleteTask(0);
            fail();
        } catch (NoSuchTaskException e) {
            assertEquals(MESSAGE_NO_SUCH_TASK, e.getMessage());
        }
    }

    @Test
    public void testNumOfTasks() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        Event event = new Event("event", dateTime);
        Deadline deadline = new Deadline("deadline", dateTime);
        taskList.addTask(todo);
        taskList.addTask(event);
        taskList.addTask(deadline);
        assertEquals(3, taskList.getNumOfTasks());
    }

    @Test
    public void testTasksRemaining() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        Event event = new Event("event", dateTime);
        Deadline deadline = new Deadline("deadline", dateTime);
        taskList.addTask(todo);
        taskList.addTask(event);
        taskList.addTask(deadline);
        String expected = "Now you have 3 tasks in the list";
        assertEquals(expected, taskList.tasksRemaining());
    }
}
