package duke.command;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {};

    /**
     * Prints out list of valid commands.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return the Duke response to show user
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = "Here is the list of commands:"
                + "\nExit duke:\n - bye"
                + "\nDisplay list of tasks:\n - list"
                + "\nDelete task:\n - delete <task id>"
                + "\nSet task as completed:\n - done <task id>"
                + "\nFilter tasks by keyword keyword:\n - find <keyword>"
                + "\nFilter tasks by due date:\n - filter <dd/mm/yyyy>"
                + "\nAdd a new task:\n - todo <task>"
                + "\nAdd a new deadline:\n - deadline <task> /by <dd/mm/yyyy> <hhmm>"
                + "\nAdd a new event:\n - event <name> /at <dd/mm/yyyy> <hhmm>"
                + "\nAdd a new weekly recurring task:\n - weekly <name> /every <day> <hhmm>";
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}