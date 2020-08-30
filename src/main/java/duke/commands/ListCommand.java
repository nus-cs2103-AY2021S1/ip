package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that shows all tasks in the list.
 * @version 1.0
 */
public class ListCommand extends Command {
    /**
     * Creates a new ListCommand.
     */
    public ListCommand() {
        this.commandName = "FullList";
        this.isExit = false;
    }

    /**
     * Prints all tasks in the specified TaskList.
     * Shows action feedback to user.
     *
     * @param list A TaskList object of which the command is executed on.
     * @param ui An UI object to interact with the user if required by the command.
     * @param storage A Storage object to write/access information to/from a file if required by the command.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showList();
        list.printList("All");
    }
}
