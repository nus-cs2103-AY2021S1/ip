public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfTasks = tasks.size();
        if (tasks.isEmpty()) {
            ui.showListNoTasks();
        } else {
            ui.showListWithTasksHeader();
            for (int i = 1; i <= numOfTasks; i++) {
                ui.showTaskWithIndex(i, tasks.getTask(i));
            }
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
