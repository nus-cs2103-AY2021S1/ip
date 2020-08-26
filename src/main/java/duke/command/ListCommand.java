package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Class representing a command to list out the current tasks.
 */
public class ListCommand extends Command {
    /**
     * Lists out the current tasks.
     * @param tasks List of tasks.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.botOutput(tasks.toString());
    }
}
