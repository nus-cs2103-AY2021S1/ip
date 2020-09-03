package pandabot.commands;

import pandabot.storage.Storage;
import pandabot.tasks.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a find command which allows users to search for tasks with description
 * that matches the search word.
 */
public class FindCommand extends Command {
    private final  String toMatch;

    /**
     * Creates a FindCommand object.
     *
     * @param toMatch the word used in the search for tasks with matching descriptions
     */
    public FindCommand(String toMatch) {
        this.toMatch = toMatch;
    }

    /**
     * Executes the finding of tasks. The user will be notified of the matching tasks
     * through the printed messages by the ui.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used
     * @param storage the current Storage object being used
     * @return the String representation to display
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTask(toMatch);
        return ui.displayOnFind(matchingTasks);
    }
}
