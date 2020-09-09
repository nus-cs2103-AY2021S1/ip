package main.command;

import java.util.Arrays;

import main.task.Task;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the done command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.2
 */
public class FindCommand implements Command {
    private final String[] searchTerms;

    /**
     * Constructs a FindCommand instance with the string to search for.
     * @param searchTerms the strings to search the task list for.
     */
    public FindCommand(String searchTerms) {
        this.searchTerms = searchTerms.split(" ");
    }

    /**
     * Looks through the task list and constructs a new list with the
     * tasks that have names that contain the search terms.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @return the string showing all tasks found.
     */
    @Override
    public String execute(Ui ui, TaskList tasks) {
        TaskList foundTasks = new TaskList();
        boolean isValidSearchTerms = searchTerms[0].length() > 0;

        if (isValidSearchTerms) {
            for (int i = 0; i < tasks.size(); i++) {
                foundTasks.add(tasks.get(i));
            }

            for (String searchTerm : searchTerms) {
                TaskList tempTaskList = new TaskList();

                for (int i = 0; i < foundTasks.size(); i++) {
                    Task task = foundTasks.get(i);
                    boolean isFoundInName = task.getName().contains(searchTerm);
                    boolean isFoundInTags = Arrays
                            .stream(task.getTags())
                            .parallel()
                            .anyMatch(tag -> tag.contains(searchTerm));
                    boolean isFound = isFoundInName || isFoundInTags;

                    if (isFound) {
                        tempTaskList.add(task);
                    }
                }

                foundTasks = tempTaskList;
            }
        }

        return ui.printFoundList(foundTasks);
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommandAfter() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand) {
            FindCommand o = (FindCommand) obj;
            return Arrays.equals(this.searchTerms, o.searchTerms);
        }
        return false;
    }
}
