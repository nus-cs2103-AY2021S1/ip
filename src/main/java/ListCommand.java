public class ListCommand extends Command {
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
