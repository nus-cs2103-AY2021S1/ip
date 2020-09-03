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
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        test.execute(list, ui, storage);
    }
    
    @Test
    public void testExecute_exceptionDateFormatThrown() {
        String[] command = {"deadline", "test /by tuesday"};
        DeadlineCommand test = new DeadlineCommand(command);
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        test.execute(list, ui, storage);
    }
}
