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

public class ListCommandTest {
    private static final Ui UI = new Ui();
    private static Deadline taskTwo;
    private static TaskList tasks;

    @BeforeEach
    public void beforeEach() throws InvalidOptionException {
        Todo taskOne = new Todo("task 1", true, new String[0]);
        taskTwo = new Deadline(
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
        @DisplayName("should return a string with list of tasks")
        public void execute_taskList_stringOfTasks() {
            assertEquals(UI.printTaskList(tasks),
                    new ListCommand().execute(UI, tasks));
        }

        @Test
        @DisplayName("should return an alternate string with an alternate list")
        public void execute_altList_altStringOfTasks() {
            TaskList altList = new TaskList();
            altList.add(taskTwo);
            assertEquals(UI.printTaskList(altList),
                    new ListCommand().execute(UI, altList));
        }

        @Test
        @DisplayName("should return a no tasks found string if list is empty")
        public void execute_emptyList_noTasksFound() {
            TaskList emptyList = new TaskList();
            assertEquals(UI.printTaskList(emptyList),
                    new ListCommand().execute(UI, emptyList));
        }
    }

    @Nested
    @DisplayName("has command after")
    class HasCommandAfter {
        @Test
        @DisplayName("should return true")
        public void hasCommandAfter_noInput_true() {
            assertTrue(new ListCommand().hasCommandAfter());
        }
    }
}
