package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void handleDeadline_wrongDateFormat_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            CommandName deadline = CommandName.DEADLINE;
            tasks.addTask(deadline, "desc", "2020/10/10");
            fail();
        } catch (DukeException e) {
            assertEquals("     OOPS!!! Pass in a date in yyyy-mm-dd :-(", e.getMessage());
        }
    }
}
