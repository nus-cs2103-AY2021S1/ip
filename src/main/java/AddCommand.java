public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        super("add");
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        // ui.showAddMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
