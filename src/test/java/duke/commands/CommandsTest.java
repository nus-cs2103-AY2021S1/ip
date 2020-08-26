package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsTest {
    @Test
    public void testAddCommand() {
        AddCommand addTodo = new AddCommand(AddCommand.Type.TODO, "read book");
        AddCommand addDeadline = new AddCommand(AddCommand.Type.DEADLINE, "read book /by 12-12-2020 23:54");
        AddCommand addEvent = new AddCommand(AddCommand.Type.EVENT, "read book /at 12-12-2020 23:54");

        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();

        addTodo.execute(taskList, ui, storage);
        addDeadline.execute(taskList, ui, storage);
        addEvent.execute(taskList, ui, storage);
        assertEquals(3, taskList.size());
    }
}
