package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a command to list out the current tasks.
 */
public class ListCommand extends Command {
    /**
     * Lists out the current tasks.
     *
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assertArgumentsValid(tasks, ui, storage);
        ui.botOutput(tasks.toString());
    }
}
