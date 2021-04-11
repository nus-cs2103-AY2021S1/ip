package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The find command searches for tasks that match the input query and
 * generate the filtered list containing the tasks. The search only
 * matches entire words (space-delimited). For example,
 *     query "friend" matches task "call a friend"
 *     query "friend" does NOT match task "find girlfriend"
 */
public class FindCommand implements Command {

    private final String query;

    /**
     * Constructs the find command which will search for tasks in the task list.
     * @param query the keyword(s) to be used in the search.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = tasks.getFilteredListMessage(query);
        ui.sendMessage(msg);
        return msg;
    }
}
