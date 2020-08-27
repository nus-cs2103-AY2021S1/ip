/**
 * Represents a command to list current items in tasks list.
 */
public class ListCommand extends Command {

    /**
     * Executes command to print out all tasks in the tasks list, or notify an empty task list.
     * @param tasks Task list of all tasks.
     * @param ui Ui to deal with interaction with user.
     * @param storage Storage to load and save tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getNumTasks() == 0) {
            ui.showEmptyTaskList();
        } else {
            String tasksList = "";
            for (int i = 0; i < tasks.getNumTasks(); i++) {
                if (i == 0) {
                    tasksList = " 1. " + tasks.getTask(i + 1);
                } else {
                    tasksList = tasksList + "\n " + (i + 1) + ". " + tasks.getTask(i + 1);
                }
            }
            ui.showTaskList(tasksList);
        }
    }

    public boolean isExit() {
        return false;
    }
}
