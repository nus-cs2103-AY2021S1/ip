package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeInvalidIndexException;
import duke.task.Task;
import duke.TaskList;

import java.io.IOException;

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
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(this.task);
            storage.save(tasks);
            ui.showAdded(this.task, tasks.getSize());
        } catch (IOException | DukeInvalidIndexException e) {
            ui.showError(e);
        }
        return true;
    }
}
