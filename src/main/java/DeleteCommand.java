public class DeleteCommand extends Command{

    int index;
    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removed = tasks.deleteTask(index);
        ui.showDelete(removed, tasks.numTask());
    }

    @Override
    boolean isExit() {
        return false;
    }
}
