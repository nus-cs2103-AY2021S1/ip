package duke.command;

import duke.exception.DukeException;
import duke.storage.StateManager;
import duke.task.TaskList;

public class RedoCommand extends Command {
    @Override
    public CommandResult execute(TaskList taskList, StateManager stateManager) throws DukeException {
        TaskList newTaskList = stateManager.redo();
        taskList.updateTaskList(newTaskList);
        return new CommandResult("Successfully redid task!");
    }
}
