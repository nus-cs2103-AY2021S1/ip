package main.command;

import main.task.Task;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the done command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.2
 */
public class FindCommand implements Command {
    private final String searchTerm;

    /**
     * Constructs a FindCommand instance with the string to search for.
     * @param searchTerm the string to search the task list for.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Looks through the task list and constructs a new list with the
     * tasks that have names that contain the search terms.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     */
    @Override
    public void execute(Ui ui, TaskList tasks) {
        TaskList found = new TaskList();
        if (searchTerm.length() > 0) {
            for (int j = 0; j < tasks.size(); j++) {
                Task task = tasks.get(j);
                if (task.getName().contains(searchTerm)) {
                    found.add(task);
                }
            }
        }
        ui.printFoundList(found);
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommand() {
        return true;
    }
}
