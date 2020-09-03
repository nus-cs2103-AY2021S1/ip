package main.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.task.TaskList;
import main.ui.Ui;

public class ExitCommandTest {
    private static final Ui UI = new Ui();
    private static final TaskList tasks = new TaskList();

    @Nested
    @DisplayName("execute")
    class Execute {
        @Test
        @DisplayName("should return the exit message")
        public void execute_noInput_exitMessage() {
            assertEquals("Bye. Hope to see you again soon!",
                    new ExitCommand().execute(UI, tasks));
        }
    }

    @Nested
    @DisplayName("has command after")
    class HasCommandAfter {
        @Test
        @DisplayName("should return true")
        public void hasCommandAfter_noInput_true() {
            assertFalse(new ExitCommand().hasCommandAfter());
        }
    }
}
