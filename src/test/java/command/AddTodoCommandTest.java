package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;


public class AddTodoCommandTest {
    @Test
    public void testExecute_exceptionThrown() {
        String[] command = {"todo", "100"};
        AddTodoCommand test = new AddTodoCommand(command);
        try {
            TaskList list = new TaskList(new LinkedList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.\n", e.toString());
        }
    }


}
