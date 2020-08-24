public abstract class AddCommand extends Command {

    protected String taskDetails;
    protected AddCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    protected void addTask(Task newTask, TaskList tasks, Ui ui, Storage storage) {
        tasks.add(newTask);
        ui.addTask(newTask, tasks.size());
        storage.update(tasks);
    }
}
