public class DeleteCommand extends Command{

    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = tasks.get(index);
        tasks.remove(index);
        ui.showRemovalMessage(task, tasks);
    }
}
