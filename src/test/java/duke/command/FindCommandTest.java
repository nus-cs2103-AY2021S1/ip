package duke.command;

import static org.junit.jupiter.api.Assertions.fail;

import duke.storage.StateManager;
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
            StateManager stateManager = new StateManager("data/duke.txt");
            Task todo = new ToDo("description");
            Command command = new AddCommand(todo);
            command.execute(taskList, stateManager);
            Command findCommand = new FindCommand("description");
            findCommand.execute(taskList, stateManager);
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
