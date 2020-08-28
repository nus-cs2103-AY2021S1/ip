package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidTodoException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskFactoryTest {
    @Test
    public void createTask_createValidTodo_success() throws DukeException {
        TaskFactory.createTask(TaskType.Todo, "This is a test.");
    }

    @Test
    public void createTask_createInvalidTodo_exceptionThrown() {
        assertThrows(InvalidTodoException.class, () -> {
            TaskFactory.createTask(TaskType.Todo, "");
        });
    }

    @Test
    public void createEvent_createValidEvent_success() throws DukeException {
        TaskFactory.createTask(TaskType.Event, "This is a test /at 2020-02-05 13:00");
    }

    @Test
    public void createEvent_createInvalidEvent_exceptionThrown() {
        assertThrows(InvalidEventException.class, () -> {
            TaskFactory.createTask(TaskType.Event, "This is a test /at 2020/02/05 13:00");
        });
    }

    @Test
    public void createDeadline_createValidDeadline_success() throws DukeException {
        TaskFactory.createTask(TaskType.Deadline, "This is a test /by 2020-02-05 13:00");
    }

    @Test
    public void createEvent_createInvalidDeadline_exceptionThrown() {
        assertThrows(InvalidDeadlineException.class, () -> {
            TaskFactory.createTask(TaskType.Deadline, "This is a test /at 2020-02-05 13:00");
        });
    }

    @Test
    public void createEvent_invalidTaskType_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> {
            TaskFactory.createTask(TaskType.Invalid, "This is a test");
        });
    }
}
