package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class AddDeadlineCommandTest {
    @Test
    public void testExecute_exceptionThrown() {
        String[] command = {"deadline", ""};
        AddDeadlineCommand test = new AddDeadlineCommand(command);
        try {
            TaskList list = new TaskList(new LinkedList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.\n", e.toString());
        }
    }

    @Test
    public void testExecute_wrongFormat_exceptionThrown() {
        String[] command = {"deadline", "return book /by invalide"};
        AddDeadlineCommand test = new AddDeadlineCommand(command);
        try {
            TaskList list = new TaskList(new LinkedList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please use the dd/MM/yyyy HHmm format.\n", e.toString());
        }
    }
}
