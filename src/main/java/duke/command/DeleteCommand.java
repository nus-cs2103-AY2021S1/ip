package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int indexOfTask;

    /**
     * Initializes the command with the index of the task to delete.
     *
     * @param indexOfTask The index of the task to delete.
     */
    public DeleteCommand(int indexOfTask) {
        this.indexOfTask = indexOfTask;
    }


    /**
     * Deletes the task in the TaskList, prints a message in the Ui, and updates the Storage.
     *
     * @param taskList The TaskList used by Duke.
     * @param storage  The Storage used by Duke.
     * @return CommandResult object for ui
     * @throws DukeException If fail to delete or to update storage.
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null && storage != null;
        try {
            Task taskToDelete = taskList.getTask(indexOfTask);
            taskList.deleteTask(indexOfTask);
            storage.updateStorage(taskList);
            CommandResult commandResult = new CommandResult("Noted. I've removed this task.\n"
                    + taskToDelete.toString() + "\n"
                    + "Now you have " + taskList.numberOfTasks() + " tasks in the list.");
            return commandResult;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Sorry, I couldn't find the task.");
        }
    }

}
