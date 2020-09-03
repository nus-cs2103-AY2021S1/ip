package main.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.task.Deadline;
import main.task.Event;
import main.task.TaskList;
import main.task.Todo;
import main.ui.Ui;

public class ListCommandTest {
    private static final Ui UI = new Ui();
    private static final Todo TASK_ONE = new Todo("task 1", true);
    private static final Deadline TASK_TWO = new Deadline("task 2",
            LocalDateTime.of(193, 7, 26, 13, 50));
    private static final Event TASK_THREE = new Event(
            "task 3", "1993-12-06T10:10", false);
    private static TaskList tasks;

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
        @DisplayName("should return a string with list of tasks")
        public void execute_taskList_stringOfTasks() {
            assertEquals(UI.printTaskList(tasks),
                    new ListCommand().execute(UI, tasks));
        }

        @Test
        @DisplayName("should return an alternate string with an alternate list")
        public void execute_altList_altStringOfTasks() {
            TaskList altList = new TaskList();
            altList.add(TASK_TWO);
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
