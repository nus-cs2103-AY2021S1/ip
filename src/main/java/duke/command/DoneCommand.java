package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int indexOfTask;

    /**
     * Initializes the command with the index of task to set as done.
     *
     * @param indexOfTask Index of the task to set as done.
     */
    public DoneCommand(int indexOfTask) {
        this.indexOfTask = indexOfTask;
    }

    /**
     * Sets the task at the indexOfTask in the TaskList as done, prints a message in the Ui
     * and updates the Storage.
     *
     * @param taskList The TaskList used by Duke.
     * @param storage  The Storage used by Duke.
     * @return CommandResult object for ui
     * @throws DukeException If unable to find the task or update the storage.
     */

    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws DukeException {
        Task taskToSetDone = taskList.getTask(indexOfTask);
        taskList.setTaskDone(indexOfTask);
        storage.updateStorage(taskList);
        return new CommandResult("Nice! I've marked this task as done:\n"
                + taskToSetDone.toString());
    }
}
