package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the task list and rewrites the file in filepath specified in storage
     * to exclude the deleted task.
     *
     * @param tasks duke.tasks.Task list of all tasks.
     * @param ui duke.ui.Ui to deal with interaction with user.
     * @param storage duke.storage.Storage to load and save tasks.
     * @return The output string message showing deleted task and current tasks left.
     * @throws DukeException If the task number is invalid or unable to overwrite file specified in storage
     * filePath successfully.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "Tasklist not found.";
        assert ui != null : "duke.ui.Ui not found.";
        assert storage != null : "duke.storage.Storage not found.";

        // check if the taskNumber provided is in range
        if (taskNumber > tasks.getNumTasks() || taskNumber <= 0) {
            throw new InvalidTaskException();
        }

        String output = ui.showDeleted(tasks.getTask(taskNumber), (tasks.getNumTasks() - 1));
        tasks.deleteTask(taskNumber); // delete from tasklist
        storage.overwriteFile(tasks.getTaskList()); // delete from storage
        assert output != null : "Message to show task deleted should be shown.";
        return output;
    }

    public boolean isExit() {
        return false;
    }
}
