package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;
import org.junit.jupiter.api.Test;
import Exception.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void testExecute_DeleteOutOfBoundExceptionThrown() {
        String[] command = {"delete", "100"};
        DeleteCommand test = new DeleteCommand(command);
        try {
            TaskList list= new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! There are no such task.\n",e.toString());
        }
    }

    @Test
    public void testExecute_DeleteUnknownExceptionThrown() {
        String[] command = {"delete", "asdf"};
        DeleteCommand test = new DeleteCommand(command);
        try {
            TaskList list= new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("Please provide the number of the task to be deleted\n",e.toString());
        }
    }


}
