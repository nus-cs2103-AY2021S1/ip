package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that will find a list of tasks corresponding to the given keyword.
 */
public class FindCommand extends Command {
    /**
     * The user given input.
     */
    private final String input;

    /**
     * Initializes a new instance of a FindCommand.
     *
     * @param input The keyword to search for.
     */
    public FindCommand(String input) {
        this.input = input.trim();
    }

    /**
     * Executes the find command by searching for tasks with the keyword in the taskList and
     * then prints a message either listing the matching tasks or stating that no tasks were found.
     *
     * @param taskList  The list of tasks known by the chat bot.
     * @param ui        The Ui that is used by the chat bot.
     * @param storage   The storage used by the chat bot.
     * @return          Chat bot message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filtered = taskList.findTask(input);
        if (filtered.size() == 0) {
            return ui.replyNoTasksFound();
        } else {
            return ui.replyFoundTasks(filtered.toString());
        }
    }
}
