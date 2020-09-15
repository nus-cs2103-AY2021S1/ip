package duke.command;

import static duke.util.Keyword.COMMAND_LIST;
import static duke.util.Keyword.HEADER;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Prints out all commands to the user.
 */
public class HelpCommand extends Command {

    /**
     * Displays the list of commands.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printNumberedArray(COMMAND_LIST, HEADER);
    }
}
