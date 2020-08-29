package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;


public class DeleteCommandTest {
    @Test
    public void testExecute_deleteOutOfBoundExceptionThrown() {
        String[] command = {"delete", "100"};
        DeleteCommand test = new DeleteCommand(command);
        try {
            TaskList list = new TaskList(new LinkedList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! There are no such task.\n", e.toString());
        }
    }

    @Test
    public void testExecute_deleteUnknownExceptionThrown() {
        String[] command = {"delete", "asdf"};
        DeleteCommand test = new DeleteCommand(command);
        try {
            TaskList list = new TaskList(new LinkedList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals("Please provide the number of the task to be deleted\n", e.toString());
        }
    }


}
