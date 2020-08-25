public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        Task.reduceOneTasks();
        ui.displayDeleteMessage(task);
        tasks.deleteTask(index);
    }

}
