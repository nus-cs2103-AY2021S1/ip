package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command to exit duke.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to perform its appropriate action.
     * @param tasks Duke task list.
     * @param ui Ui object to print user messages.
     * @param storage Storage object to load and save tasks to data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.isExit = true;
        storage.save(tasks.convertToFile());
        return ui.showExit();
    }
}
