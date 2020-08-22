package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import org.junit.jupiter.api.Test;

import Exception.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void testExecute_DoneOutOfBoundExceptionThrown() {
        String[] command = {"done", "100"};
        DoneCommand test = new DoneCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! There are no such task.\n",e.toString());
        }
    }

    @Test
    public void testExecute_DoneUnknownExceptionThrown() {
        String[] command = {"done", "asdf"};
        DoneCommand test = new DoneCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("Please provide the number of the task to be marked\n",e.toString());
        }
    }
}
