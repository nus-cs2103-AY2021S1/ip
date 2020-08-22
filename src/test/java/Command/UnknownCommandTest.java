package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;
import org.junit.jupiter.api.Test;
import Exception.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnknownCommandTest {
    @Test
    public void testExecute_exceptionThrown() {
        String[] command = {"Unknown command", "100"};
        UnknownCommand test = new UnknownCommand(command);
        try {
            TaskList list= new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! I don't know what that commands mean.\n",e.toString());
        }
    }
}
