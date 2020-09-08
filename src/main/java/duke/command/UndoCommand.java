package duke.command;

import duke.exception.DukeException;
import duke.storage.StateManager;
import duke.task.TaskList;

public class UndoCommand extends Command {

    public UndoCommand() {}

    @Override
    public CommandResult execute(TaskList taskList, StateManager stateManager) throws DukeException {
        TaskList newTaskList = stateManager.undo();
        taskList.updateTaskList(newTaskList);
        return new CommandResult("Successfully undid task!");
    }
}
