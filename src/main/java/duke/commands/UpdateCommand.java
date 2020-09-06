package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.tasks.Task;

/**
 * Command that represents the intent to update a task in the taskList.
 */
public class UpdateCommand implements Command {

    private final int taskNum;
    private final String updateString;

    /**
     * Initializes an UpdateCommand.
     *
     * @param taskNum      The number of the task in the taskList to be updated.
     * @param updateString The string that represents the information of the updated task.
     */
    public UpdateCommand(int taskNum, String updateString) {
        this.taskNum = taskNum;
        this.updateString = updateString;
    }

    /**
     * Updates the task in the taskList.
     *
     * @param storage The storage object.
     * @param tasks   The taskList.
     * @return The string represetation of the updated task.
     * @throws DukeException If updateString format is wrong.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        Task updatedTask = tasks.update(taskNum, updateString);
        return "Successfully updated task #" + taskNum + ": " + updatedTask.toString();
    }
}
