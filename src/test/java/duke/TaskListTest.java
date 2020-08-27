package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

public class TaskListTest {
    private static final String DESCRIPTION = "description";
    private static final LocalDateTime DATE_TIME = LocalDateTime.now();

    @Test
    public void addTodo_validTodo_todoReturned() throws DukeException {
        Task expectedTask = new Todo(TaskListTest.DESCRIPTION);
        TaskList taskList = new TaskList();

        taskList.addTask(TaskType.TODO, TaskListTest.DESCRIPTION, null);
        assertEquals(expectedTask, taskList.getTask(1));
    }

    @Test
    public void addDeadline_validDeadline_deadlineReturned() throws DukeException {
        Task expectedTask = new Deadline(TaskListTest.DESCRIPTION, TaskListTest.DATE_TIME);
        TaskList taskList = new TaskList();

        taskList.addTask(TaskType.DEADLINE, TaskListTest.DESCRIPTION, TaskListTest.DATE_TIME);
        assertEquals(expectedTask, taskList.getTask(1));
    }

    @Test
    public void addEvent_validEvent_eventReturned() throws DukeException {
        Task expectedTask = new Event(TaskListTest.DESCRIPTION, TaskListTest.DATE_TIME);
        TaskList taskList = new TaskList();

        taskList.addTask(TaskType.EVENT, TaskListTest.DESCRIPTION, TaskListTest.DATE_TIME);
        assertEquals(expectedTask, taskList.getTask(1));
    }

    @Test
    public void addTask_invalidTask_exceptionThrown() {
        TaskList taskList = new TaskList();

        assertThrows(DukeException.class, () -> taskList.addTask(TaskType.TODO, "", null));
        assertThrows(DukeException.class, () -> taskList.addTask(TaskType.DEADLINE, "", TaskListTest.DATE_TIME));
        assertThrows(DukeException.class, () -> taskList.addTask(TaskType.EVENT, "", TaskListTest.DATE_TIME));
    }

    @Test
    public void getTask_invalidTaskNo_exceptionThrown() {
        TaskList taskList = new TaskList();

        assertThrows(DukeException.class, () -> taskList.getTask(0));
        assertThrows(DukeException.class, () -> taskList.getTask(1));
    }

    @Test
    public void deleteTask_validTaskNo_taskDeleted() throws DukeException {
        List<Task> tasks = Arrays.asList(
                new Todo(TaskListTest.DESCRIPTION),
                new Deadline(TaskListTest.DESCRIPTION, TaskListTest.DATE_TIME),
                new Event(TaskListTest.DESCRIPTION, TaskListTest.DATE_TIME));
        TaskList taskList = new TaskList(tasks);
        int initialSize = taskList.getTasks().size();

        taskList.deleteTask(1);
        assertEquals(initialSize - 1, taskList.getTasks().size());
    }
}
