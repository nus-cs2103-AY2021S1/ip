package duke.command;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class DeleteCommandTest {
    @Test
    public void successfulDelete() {
        try {
            TaskList taskList = new TaskList();
            Storage storage = new Storage("data/duke.txt");
            Task todo = new ToDo("description");
            Command command = new AddCommand(todo);
            command.execute(taskList, storage);
            Command deleteCommand = new DeleteCommand(0);
            deleteCommand.execute(taskList, storage);
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void wrongIndex() {
        try {
            TaskList taskList = new TaskList();
            Storage storage = new Storage("data/duke.txt");
            Command deleteCommand = new DeleteCommand(-1);
            deleteCommand.execute(taskList, storage);
            fail("Index is out of bounds and should have thrown an exception");
        } catch (DukeException e) {
            return;
        }
    }

}
