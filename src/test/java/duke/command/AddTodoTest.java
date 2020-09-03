package duke.command;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddTodoTest {
    @Test
    public void testExecute_exceptionEmptyThrown() {
        String[] command = {"todo"};
        TodoCommand test = new TodoCommand(command);
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        test.execute(list, ui, storage);
    }
}
