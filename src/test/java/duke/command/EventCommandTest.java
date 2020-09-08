package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventCommandTest {
    @Test
    void execute_validCommand_addEventTaskToTaskList() throws DukeException {
        TaskList tasks = new TaskList();
        Command cmd = new EventCommand("event return book /at 2020-07-29");
        cmd.execute(tasks);
        assertEquals(tasks.get(1).toString(), "[E][ ] return book (at: Jul 29 2020)");
    }

    @Test
    void execute_invalidCommandNoDescription_throwsException() {
        TaskList tasks = new TaskList();
        Command cmd1 = new EventCommand("event ");
        assertThrows(DukeException.class, () ->
                cmd1.execute(tasks)
        );

        Command cmd2 = new EventCommand("event");
        assertThrows(DukeException.class, () ->
                cmd2.execute(tasks)
        );
    }
}