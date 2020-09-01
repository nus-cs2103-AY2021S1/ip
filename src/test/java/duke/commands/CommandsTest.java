package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.TaskList;


public class CommandsTest {
    @Test
    public void testAddCommand() {
        AddCommand addTodo = new AddCommand(AddCommand.Type.TODO, "read book");
        AddCommand addDeadline = new AddCommand(AddCommand.Type.DEADLINE, "read book /by 12-12-2020 23:54");
        AddCommand addEvent = new AddCommand(AddCommand.Type.EVENT, "read book /at 12-12-2020 23:54");

        Storage storage = new Storage();
        TaskList taskList = new TaskList();

        addTodo.execute(taskList, storage);
        addDeadline.execute(taskList, storage);
        addEvent.execute(taskList, storage);
        assertEquals(3, taskList.size());
    }
}
