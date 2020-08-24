public class AddTaskCommand implements Command {
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        tasks.addTask(task);
        ui.print("Got it. I've added this task:\n  " + task.toString() + "\nNow you have " + tasks.size()
                + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
