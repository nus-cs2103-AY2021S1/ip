package main.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.exception.InvalidOptionException;
import main.task.Deadline;
import main.task.Event;
import main.task.TaskList;
import main.task.Todo;
import main.ui.Ui;

public class TodoCommandTest {
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
        @DisplayName("should add task to list and return add success string")
        public void execute_todoTask_addSuccess() {
            String name = "test";
            TodoCommand command = new TodoCommand(name, new String[0]);
            Todo task = new Todo(name, new String[0]);

            assertEquals(3, tasks.size());
            assertEquals(UI.printAddSuccess(task, 4), command.execute(UI, tasks));
            assertEquals(4, tasks.size());
            assertEquals(task, tasks.get(3));
        }

        @Test
        @DisplayName("should add alternate task to list and return add success string")
        public void execute_altTodoTask_addSuccess() {
            String name = "test 2";
            TodoCommand command = new TodoCommand(name, new String[0]);
            Todo task = new Todo(name, new String[0]);

            assertEquals(3, tasks.size());
            assertEquals(UI.printAddSuccess(task, 4), command.execute(UI, tasks));
            assertEquals(4, tasks.size());
            assertEquals(task, tasks.get(3));
        }
    }

    @Nested
    @DisplayName("has command after")
    class HasCommandAfter {
        @Test
        @DisplayName("should return true")
        public void hasCommandAfter_noInput_true() {
            assertTrue(new EventCommand(
                    "test",
                    LocalDateTime.of(1992, 10, 3, 19, 2),
                    new HashSet<>(),
                    new String[0]
                ).hasCommandAfter());
        }
    }
}
