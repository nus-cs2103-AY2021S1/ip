/**
 * Represents a command to find tasks based on keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Obtains string of matching tasks.
     *
     * @param tasks Task List of all tasks.
     * @return List of tasks, numbered in order.
     */
    public String getMatchingTasks(TaskList tasks) {
        String matchingTasks = "";
        int numMatching = 0;

        for (int i = 0; i < tasks.getNumTasks(); i++) {
            Task currentTask = tasks.getTask(i + 1);

            if (currentTask.getDescription().contains(keyword)) {
                numMatching++;
                matchingTasks = matchingTasks + "\n " + numMatching + ". " + currentTask.toString();
            }
        }
        return matchingTasks;
    }

    /**
     * Search for tasks with keyword and enumerates them.
     *
     * @param tasks Task list of all tasks.
     * @param ui Ui to deal with interaction with user.
     * @param storage Storage to load and save tasks.
     * @return List of tasks, numbered in order.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasklist not found.";
        assert ui != null : "Ui not found.";
        assert storage != null : "Storage not found.";
        assert !keyword.isEmpty() : "No keyword provided.";

        String matchingTasks = getMatchingTasks(tasks);

        if (matchingTasks.isEmpty()) {
            assert ui.showEmptyMatchingList() != null : "Message showing empty list should be shown.";
            return ui.showEmptyMatchingList();
        } else {
            assert ui.showMatchingTaskList(matchingTasks) != null : "Message showing "
                    + "matching task list should be shown.";
            return ui.showMatchingTaskList(matchingTasks);
        }
    }

    public boolean isExit() {
        return false;
    }
}
