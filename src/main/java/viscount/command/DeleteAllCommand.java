package viscount.command;

import java.util.List;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountSaveDataException;
import viscount.task.Task;

/**
 * Represents a delete all command.
 */
public class DeleteAllCommand extends DeleteCommand {
    /**
     * Instantiates a new delete all command.
     */
    public DeleteAllCommand() {
        super(null);
    }

    /**
     * Executes the delete all command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountSaveDataException If exception occurs with writing to disk.
     */
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage)
            throws ViscountSaveDataException {
        List<Task> tasksBeforeDelete = taskList.deleteAndGetAllTasks();
        storage.saveToDisk(taskList.getTasks());
        return ui.getDeleteAllResponse(tasksBeforeDelete);
    }
}
