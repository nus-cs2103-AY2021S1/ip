public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            System.out.println("Cannot write to file!");
        }
        ui.showAdd(task, tasks);
    }
}
