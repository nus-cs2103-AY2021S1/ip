/**
 * Represents a command to find tasks based on keyword.
 */
public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Search for tasks with keyword and enumerates them.
     *
     * @param tasks Task list of all tasks.
     * @param ui Ui to deal with interaction with user.
     * @param storage Storage to load and save tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String matchingTasks = "";
        int numMatching = 0;

        for (int i = 0; i < tasks.getNumTasks(); i++) {
            Task currentTask = tasks.getTask(i + 1);
            if (currentTask.getDescription().contains(keyword)) {
                numMatching++;
                if (numMatching == 1) {
                    matchingTasks = " 1. " + currentTask.toString();
                } else {
                    matchingTasks = matchingTasks + "\n " + numMatching + ". " + currentTask.toString();
                }
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showEmptyMatchingList();
        } else {
            ui.showMatchingTaskList(matchingTasks);
        }
    }

    public boolean isExit() {
        return false;
    }
}
