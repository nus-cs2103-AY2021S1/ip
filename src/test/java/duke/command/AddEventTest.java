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
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        test.execute(list, ui, storage);
    }

    @Test
    public void testExecute_exceptionDateFormatThrown() {
        String[] command = {"event", "test /by tuesday"};
        EventCommand test = new EventCommand(command);
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        test.execute(list, ui, storage);
    }
}
