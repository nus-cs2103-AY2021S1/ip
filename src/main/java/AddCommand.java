public class AddCommand extends Command {
    Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);

        storage.saveNewTask(task);

        ui.print(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task,
                tasks.size()));
    }
}
