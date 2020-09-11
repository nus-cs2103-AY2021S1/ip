package viscount.command;

import java.util.List;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountSaveDataException;
import viscount.task.Task;

/**
 * Represents a done all command.
 */
public class DoneAllCommand extends DoneCommand {
    /**
     * Instantiates a new done all command.
     */
    public DoneAllCommand() {
        super(null);
    }

    /**
     * Executes the done all command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountSaveDataException If exception occurs with writing to disk.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage)
            throws ViscountSaveDataException {
        List<Task> notDoneTasks = taskList.markAndGetAllNotDoneTasks();
        storage.saveToDisk(taskList.getTasks());
        return ui.getDoneAllResponse(notDoneTasks);
    }
}
