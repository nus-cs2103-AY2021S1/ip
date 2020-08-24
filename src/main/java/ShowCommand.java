public class ShowCommand extends Command {
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.emptyTaskList();
        } else {
            ui.showTaskList(tasks);
        }
    }
}
