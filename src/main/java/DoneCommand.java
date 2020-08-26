public class DoneCommand extends Command {
    public int index;
    
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskList().get(index);
        ui.showDone(task);
        task.markAsDone();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
