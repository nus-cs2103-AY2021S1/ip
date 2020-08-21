package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represent a "List" Command
 * A <code>ListCommand</code> object that corresponds to a command of an input "list"
 */
public class ListCommand extends Command {

    /**
     * Prints the current list of task in the taskList
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
