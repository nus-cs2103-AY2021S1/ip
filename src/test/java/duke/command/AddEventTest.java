package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddEventTest {
    @Test
    public void testExecute_exceptionEmptyThrown() {
        String[] command = {"event"};
        EventCommand test = new EventCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("test.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            Assertions.assertEquals("The description of an event cannot be empty~", e.toString());
        }
    }

    @Test
    public void testExecute_exceptionDateFormatThrown() {
        String[] command = {"event", "test /by tuesday"};
        EventCommand test = new EventCommand(command);
        try {
            TaskList list = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("test.txt");
            test.execute(list, ui, storage);
        } catch (DukeException e) {
            Assertions.assertEquals("Please input a proper date for your event~", e.toString());
        }
    }
}
