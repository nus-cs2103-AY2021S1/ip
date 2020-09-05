package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.tasks.Task;

public class UpdateCommand implements Command {

    private final int taskNum;
    private final String updateString;

    public UpdateCommand(int taskNum, String updateString) {
        this.taskNum = taskNum;
        this.updateString = updateString;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        Task updatedTask = tasks.update(taskNum, updateString);
        return "Successfully updated task #" + taskNum + ": " + updatedTask.toString();
    }
}
