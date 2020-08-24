package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineCommandTest {
    @Test
    void execute_validCommand_addDeadlineTaskToTaskList() throws DukeException {
        TaskList tasks = new TaskList();
        Command cmd = new DeadlineCommand("deadline return book /by 2020-07-29");
        cmd.execute(tasks);
        assertEquals(tasks.get(1).toString(), "[D][ ] return book (by: Jul 29 2020)");
    }

    @Test
    void execute_invalidCommandNoDescription_throwsException() {
        TaskList tasks = new TaskList();
        Command cmd1 = new DeadlineCommand("deadline ");
        assertThrows(DukeException.class, () ->
                cmd1.execute(tasks)
        );

        Command cmd2 = new DeadlineCommand("deadline");
        assertThrows(DukeException.class, () ->
                cmd2.execute(tasks)
        );
    }
}