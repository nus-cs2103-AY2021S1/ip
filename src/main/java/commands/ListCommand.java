package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

/**
 * Represents a command from the user to print the current TaskList
 */
public class ListCommand extends Command {

    /**
     * Executes the command to print the TaskList
     * @param tasks The current TaskList
     * @param ui The Ui object in use
     * @param storage The Storage object in use
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
