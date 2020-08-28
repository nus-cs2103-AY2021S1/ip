package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;


public class DoneCommandTest {
    @Test
    public void testExecute_doneOutOfBoundExceptionThrown() {
        String[] command = {"done", "100"};
        DoneCommand test = new DoneCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! There are no such task.\n", e.toString());
        }
    }

    @Test
    public void testExecute_doneUnknownExceptionThrown() {
        String[] command = {"done", "asdf"};
        DoneCommand test = new DoneCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals("Please provide the number of the task to be marked\n", e.toString());
        }
    }
}
