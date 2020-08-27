public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
