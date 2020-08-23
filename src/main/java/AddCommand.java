public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task task) {
        super();
        this.taskToAdd = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addATask(this.taskToAdd);
        storage.writeData(tasks);
        ui.printMessage("Added new task:");
        ui.printMessage(taskToAdd.toString());
        ui.printMessage("You now have " + tasks.getNumberOfTasks() + " tasks!");
    }
}
