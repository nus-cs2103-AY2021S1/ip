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

public class FindCommandTest {
    private static final Ui UI = new Ui();
    private static Todo taskOne;
    private static Deadline taskTwo;
    private static TaskList tasks;

    @BeforeEach
    public void beforeEach() throws InvalidOptionException {
        taskOne = new Todo("task 1", true, new String[] { "abc", "134" });
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
        @DisplayName("should return a string with list of tasks found with search term")
        public void execute_searchTerm_stringOfTasks() {
            assertEquals(UI.printFoundList(tasks),
                    new FindCommand("task").execute(UI, tasks));
        }

        @Test
        @DisplayName("should return an alternate string with an alternate search term")
        public void execute_altSearchTerm_altStringOfTasks() {
            TaskList foundList = new TaskList();
            foundList.add(taskTwo);
            assertEquals(UI.printFoundList(foundList),
                    new FindCommand("2").execute(UI, tasks));
        }

        @Test
        @DisplayName("should return a string with list of tasks found "
                + "with multiple search terms")
        public void execute_multipleSearchTerms_stringOfTasks() {
            TaskList foundList = new TaskList();
            foundList.add(taskOne);
            assertEquals(UI.printFoundList(foundList),
                    new FindCommand("ta 1").execute(UI, tasks));
            assertEquals(UI.printFoundList(foundList),
                    new FindCommand("1 ta").execute(UI, tasks));
        }

        @Test
        @DisplayName("should return a no tasks found string if no tasks are found")
        public void execute_noTasksSearchTerm_noTasksFound() {
            TaskList foundList = new TaskList();
            assertEquals(UI.printFoundList(foundList),
                    new FindCommand("nothing").execute(UI, tasks));
        }

        @Test
        @DisplayName("should return a no tasks found if no string passed in")
        public void execute_emptyString_noTasksFound() {
            TaskList foundList = new TaskList();
            assertEquals(UI.printFoundList(foundList),
                    new FindCommand("").execute(UI, tasks));
        }

        @Test
        @DisplayName("should return task if search term is found in tags")
        public void execute_tagTerm_stringOfTasks() {
            TaskList foundList = new TaskList();
            foundList.add(taskOne);

            assertEquals(UI.printFoundList(foundList),
                    new FindCommand("ab 34").execute(UI, tasks));
        }
    }

    @Nested
    @DisplayName("has command after")
    class HasCommandAfter {
        @Test
        @DisplayName("should return true")
        public void hasCommandAfter_noInput_true() {
            assertTrue(new FindCommand("").hasCommandAfter());
        }
    }
}
