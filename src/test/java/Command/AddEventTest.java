package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import org.junit.jupiter.api.Test;

import Exception.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddEventTest {
    @Test
    public void testExecute_exceptionThrown() {
        String[] command = {"event", ""};
        AddEventCommand test = new AddEventCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a event cannot be empty.\n",e.toString());
        }
    }

    @Test
    public void testExecute_WrongFormatExceptionThrown() {
        String[] command = {"event", "return book /at invalide"};
        AddEventCommand test = new AddEventCommand(command);
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
