package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Prints out all commands to the user.
 */
public class HelpCommand extends Command {

    private static final String HEADER = "Here are the available commands that I know:";
    private static final String[] listOfCommands = new String[]{
        "todo _ (e.g. todo 3)",
        "deadline 'task name' /by 'end time' (e.g. deadline Exercise /by 23-8-20)",
        "event 'task name' /at 'start time - end time' (e.g. meeting /at Sunday 2pm - 4pm)",
        "list",
        "done _ (e.g. done 4)",
        "delete _ (e.g. delete 4)",
        "find '   ' (e.g. find book)",
        "sort",
        "bye"
    };

    /**
     * Displays the list of commands.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printNumberedArray(listOfCommands, HEADER);
    }
}
