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
        String response = "List of commands:"
                + "\nbye: Exit duke"
                + "\nlist: Display list of tasks"
                + "\ndelete <id>: Delete task"
                + "\ndone <id>: Set task as completed"
                + "\nfind <keyword>: Display list of tasks that include keyword"
                + "\nfilter <date>: Display list task tasks due on date"
                + "\ntodo <name>: Add a new task"
                + "\ndeadline <name> /by <date> <time>: Add a new task with deadline"
                + "\nevent <name> /at <date> <time>: Add a new event"
                + "\nweekly <name> /every <day> <time>: Add a new weekly recurring task";
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}