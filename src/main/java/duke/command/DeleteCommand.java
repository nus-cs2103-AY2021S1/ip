package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response = "";
        if (index >= 0 && index < tasks.listSize()) {
            assert index > 0 : "Negative index value detected at execute method";
            response = tasks.taskDelete(index, ui);
            storage.save(tasks.convertToFile());
        } else {
            throw new DukeException("Invalid index entry for done command.");
        }
        return response;
    }
}
