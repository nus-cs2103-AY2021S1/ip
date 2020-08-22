package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import org.junit.jupiter.api.Test;

import Exception.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoCommandTest {
    @Test
    public void testExecute_exceptionThrown() {
        String[] command = {"todo", "100"};
        AddTodoCommand test = new AddTodoCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/duke.txt");
            test.execute(list,ui,storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.\n",e.toString());
        }
    }


}
