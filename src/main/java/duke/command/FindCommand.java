package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that will find a list of tasks containing the given keyword.
 */
public class FindCommand extends Command {
    /**
     * The keyword to search for
     */
    private final String keyword;

    /**
     * Initialises a new instance.
     *
     * @param keyword The keyword to be added.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the Find command by searching for the keyword within the task list and then
     * printing a message listing the matching tasks or a message indicating no tasks were found..
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param ui      The UI that is used by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @return A string detailing the outcome of the execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.search(this.keyword);
        if (filteredTasks.size() == 0) {
            return "No matching tasks found.";
        } else {
            return String.format("Here are the matching tasks in your list:\n%s", filteredTasks);
        }
    }
}
