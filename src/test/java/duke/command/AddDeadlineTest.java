package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddDeadlineTest {
    @Test
    public void testExecute_exceptionEmptyThrown() {
        String[] command = {"deadline"};
        DeadlineCommand test = new DeadlineCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("test.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            Assertions.assertEquals("The description of a deadline cannot be empty~", e.toString());
        }
    }
    
    @Test
    public void testExecute_exceptionDateFormatThrown() {
        String[] command = {"deadline", "test /by tuesday"};
        DeadlineCommand test = new DeadlineCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("test.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            Assertions.assertEquals("Please input a proper due date for your deadline~", e.toString());
        }
    }
}
