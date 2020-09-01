package duke.command;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class FindCommandTest {
    @Test
    public void successfulFind() {
        try {
            TaskList taskList = new TaskList();
            Storage storage = new Storage("data/duke.txt");
            Task todo = new ToDo("description");
            Command command = new AddCommand(todo);
            command.execute(taskList, storage);
            Command findCommand = new FindCommand("description");
            findCommand.execute(taskList, storage);
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
