package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Storage.Storage;
import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.Ui.Ui;

/**
 * A command to delete a specific task.
 */
public class DeleteCommand extends Command {

    /**
     * Index of the task to be deleted.
     */
    private int index;

    /**
     * Creates a delete command with the appropriate task index.
     * @param size Task index.
     */
    public DeleteCommand(int size) {
        this.index = size;
    }

    /**
     * Executes the command to perform its appropriate action.
     * @param tasks Duke task list.
     * @param ui Ui object to print user messages.
     * @param storage Storage object to load and save tasks to data file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < tasks.listSize()) {
            tasks.taskDelete(index, ui);
            storage.Save(tasks.convertToFile());
        } else {
            throw new DukeException("    Invalid index entry for done command.");
        }
    }
}
