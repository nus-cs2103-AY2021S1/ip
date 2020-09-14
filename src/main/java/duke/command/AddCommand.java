package duke.command;

import java.io.IOException;

import duke.backend.Storage;
import duke.task.TaskList;
import duke.response.Ui;
import duke.exception.DukeInvalidIndexException;
import duke.task.Task;


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
     * Returns false because command does not exit.
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(this.task);
            storage.save(tasks);
            return ui.showAdded(this.task, tasks.getSize());
        } catch (IOException | DukeInvalidIndexException e) {
            return ui.showError(e);
        }
    }
}
