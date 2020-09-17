package duke.command;

import java.io.IOException;

import duke.backend.Storage;
import duke.command.exception.DukeInvalidIndexException;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an add task action.
 */
public class AddCommand implements Command {
    private final Task task;

    /**
     * Class constructor with specified Task.
     *
     * @param task The Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Performs the corresponding action of the command.
     *
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public String execute(TaskList tasks, Response ui, Storage storage) {
        try {
            tasks.addTask(this.task);
            storage.save(tasks);
            return ui.showAdded(this.task, tasks.getSize());
        } catch (IOException | DukeInvalidIndexException e) {
            return ui.showError(e);
        }
    }
}
