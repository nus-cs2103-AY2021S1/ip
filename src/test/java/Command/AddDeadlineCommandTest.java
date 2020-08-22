package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import org.junit.jupiter.api.Test;

import Exception.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDeadlineCommandTest {
    @Test
    public void testExecute_exceptionThrown() {
        String[] command = {"deadline", ""};
        AddDeadlineCommand test = new AddDeadlineCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.\n",e.toString());
        }
    }

    @Test
    public void testExecute_WrongFormatExceptionThrown() {
        String[] command = {"deadline", "return book /by invalide"};
        AddDeadlineCommand test = new AddDeadlineCommand(command);
        try {
            TaskList list= new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please use the dd/MM/yyyy HHmm format.\n",e.toString());
        }
    }
}
