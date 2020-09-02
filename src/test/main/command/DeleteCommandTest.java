package main.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.exception.InvalidTaskException;
import main.task.Deadline;
import main.task.Event;
import main.task.Task;
import main.task.TaskList;
import main.task.Todo;
import main.ui.Ui;

public class DeleteCommandTest {
    private static final Ui UI = new Ui();
    private static TaskList tasks;
    private static final Todo TASK_ONE = new Todo("task 1", true);
    private static final Deadline TASK_TWO = new Deadline("task 2",
            LocalDateTime.of(193, 7, 26, 13, 50));
    private static final Event TASK_THREE = new Event(
            "task 3", "1993-12-06T10:10", false);

    @BeforeEach
    public void beforeEach() {
        tasks = new TaskList();
        tasks.add(TASK_ONE);
        tasks.add(TASK_TWO);
        tasks.add(TASK_THREE);
    }

    @Nested
    @DisplayName("execute")
    class Execute {
        @Test
        @DisplayName("should add task to list and return remove success string")
        public void execute_taskList_removeSuccess() throws InvalidTaskException {
            DeleteCommand command = new DeleteCommand(2);
            Task task = tasks.get(1);

            assertEquals(3, tasks.size());
            assertEquals(UI.printRemoveSuccess(task, 2), command.execute(UI, tasks));
            assertEquals(2, tasks.size());
        }

        @Test
        @DisplayName("should add alternate task to list and return remove success string")
        public void execute_altTaskList_removeSuccess() throws InvalidTaskException {
            DeleteCommand command = new DeleteCommand(1);
            Task task = tasks.get(0);

            assertEquals(3, tasks.size());
            assertEquals(UI.printRemoveSuccess(task, 2), command.execute(UI, tasks));
            assertEquals(2, tasks.size());
        }

        @Test
        @DisplayName("should throw InvalidTaskException if task number out of index")
        public void execute_taskList_throwException() {
            DeleteCommand command = new DeleteCommand(4);

            InvalidTaskException exception = assertThrows(
                    InvalidTaskException.class, () -> command.execute(UI, tasks));
            assertEquals("Your selected task does not exist!",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("has command after")
    class HasCommandAfter {
        @Test
        @DisplayName("should return true")
        public void hasCommandAfter_noInput_true() {
            assertTrue(new DeleteCommand(2).hasCommandAfter());
        }
    }
}
