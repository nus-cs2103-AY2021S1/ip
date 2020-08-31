package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command to find tasks from task list via a keyword.
 */
public class FindCommand extends Command {

    /** Stores the description of the task to be added. */
    protected String description;

    /**
     * Creates a find command.
     * @param description The description of the task to be added.
     */
    public FindCommand(String description) {
        this.description = description;
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
        tasks.findTask(ui, this.description);
    }
}
