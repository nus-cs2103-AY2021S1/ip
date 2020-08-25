public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index){
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        ui.displayMarkAsDoneMessage(task);
        tasks.completeTask(index);
    }

}
