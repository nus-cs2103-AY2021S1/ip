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
                + "\nbye: Exit duke\n"
                + "\nlist: Display list of tasks\n"
                + "\ndelete <id>: Delete task\n"
                + "\ndone <id>: Set task as completed\n"
                + "\nfind <keyword>: Display list of tasks that include keyword\n"
                + "\nfilter <date>: Display list task tasks due on date\n"
                + "\ntodo <name>: Add a new task\n"
                + "\ndeadline <name> /by <date> <time>: Add a new task with deadline\n"
                + "\nevent <name> /at <date> <time>: Add a new event\n"
                + "\nweekly <name> /every <day> <time>: Add a new weekly recurring task";
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}