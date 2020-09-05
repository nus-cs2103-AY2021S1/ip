package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    private static final String HEADER = "Here are the available commands that I know:";
    private static final String[] listOfCommands = new String[]{
        "todo _ (e.g. todo 3)",
        "deadline 'task name' /by 'end time' (e.g. deadline Exercise /by Sunday)",
        "event 'task name' /at 'start time - end time' (e.g. meeting /at Sunday 2pm - 4pm)",
        "list",
        "done _ (e.g. done 4)",
        "delete _ (e.g. delete 4)",
        "find '   ' (e.g. find book)",
        "bye",
    };

    /**
     * Displays the list of commands.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printNumberedArray(listOfCommands, HEADER);
    }
}
