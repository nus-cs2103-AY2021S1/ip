package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class UnknownCommandTest {
    @Test
    public void testExecute_exceptionThrown() {
        String[] command = {"Unknown command", "100"};
        UnknownCommand test = new UnknownCommand(command);
        try {
            TaskList list = new TaskList(new LinkedList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! I don't know what that commands mean.\n", e.toString());
        }
    }
}
