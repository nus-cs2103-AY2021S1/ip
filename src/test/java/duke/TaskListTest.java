package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void addTodo_validTodo_todoReturned() throws DukeException {
        String DESCRIPTION = "todo";
        Task expectedTask = new Todo(DESCRIPTION);
        TaskList taskList = new TaskList();

        taskList.addTask(TaskType.TODO, DESCRIPTION, null);
        assertEquals(expectedTask, taskList.getTask(1));
    }

    @Test
    public void addDeadline_validDeadline_deadlineReturned() throws DukeException {
        String DESCRIPTION = "deadline";
        LocalDateTime DATE_TIME = LocalDateTime.now();
        Task expectedTask = new Deadline(DESCRIPTION, DATE_TIME);
        TaskList taskList = new TaskList();

        taskList.addTask(TaskType.DEADLINE, DESCRIPTION, DATE_TIME);
        assertEquals(expectedTask, taskList.getTask(1));
    }

    @Test
    public void addEvent_validEvent_eventReturned() throws DukeException {
        String DESCRIPTION = "event";
        LocalDateTime DATE_TIME = LocalDateTime.now();
        Task expectedTask = new Event(DESCRIPTION, DATE_TIME);
        TaskList taskList = new TaskList();

        taskList.addTask(TaskType.EVENT, DESCRIPTION, DATE_TIME);
        assertEquals(expectedTask, taskList.getTask(1));
    }

    @Test
    public void addTask_invalidTask_exceptionThrown() {
        TaskList taskList = new TaskList();

        assertThrows(DukeException.class, () -> taskList.addTask(TaskType.TODO, "", null));
        assertThrows(DukeException.class, () -> taskList.addTask(TaskType.DEADLINE, "", LocalDateTime.now()));
        assertThrows(DukeException.class, () -> taskList.addTask(TaskType.EVENT, "", LocalDateTime.now()));
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
                new Todo("todo"),
                new Deadline("deadline", LocalDateTime.now()),
                new Event("event", LocalDateTime.now()));
        TaskList taskList = new TaskList(tasks);
        int initialSize = taskList.getTasks().size();

        taskList.deleteTask(1);
        assertEquals(initialSize - 1, taskList.getTasks().size());
    }
}
