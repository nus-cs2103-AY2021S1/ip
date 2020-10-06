package main.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.exception.InvalidOptionException;
import main.exception.InvalidTaskException;
import main.task.Deadline;
import main.task.Event;
import main.task.Task;
import main.task.TaskList;
import main.task.Todo;
import main.ui.Ui;

public class DoneCommandTest {
    private static final Ui UI = new Ui();
    private static TaskList tasks;

    @BeforeEach
    public void beforeEach() throws InvalidOptionException {
        Todo taskOne = new Todo("task 1", true, new String[0]);
        Deadline taskTwo = new Deadline(
                "task 2",
                LocalDateTime.of(193, 7, 26, 13, 50),
                new HashSet<>(),
                new String[0]
        );
        Event taskThree = new Event(
                "task 3", "", "1993-12-06T10:10", false, new String[0]);
        tasks = new TaskList();
        tasks.add(taskOne);
        tasks.add(taskTwo);
        tasks.add(taskThree);
    }

    @Nested
    @DisplayName("execute")
    class Execute {
        @Test
        @DisplayName("should mark task in list as done and return done success string")
        public void execute_index_doneSuccess() throws InvalidTaskException {
            DoneCommand command = new DoneCommand(2);
            Task task = tasks.get(1);
            String doneSuccess = command.execute(UI, tasks);

            assertEquals(3, tasks.size());
            assertEquals(UI.printDoneSuccess(task), doneSuccess);
            assertEquals(3, tasks.size());
        }

        @Test
        @DisplayName("should mark alternate task in list as done and return done success string")
        public void execute_altIndex_doneSuccess() throws InvalidTaskException {
            DoneCommand command = new DoneCommand(1);
            Task task = tasks.get(0);
            String doneSuccess = command.execute(UI, tasks);

            assertEquals(3, tasks.size());
            assertEquals(UI.printDoneSuccess(task), doneSuccess);
            assertEquals(3, tasks.size());
        }

        @Test
        @DisplayName("should throw InvalidTaskException if task number out of index")
        public void execute_invalidIndex_throwException() {
            DoneCommand command = new DoneCommand(0);

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
            assertTrue(new DoneCommand(2).hasCommandAfter());
        }
    }
}
