public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(task);
        ui.print("Got it. I've added this task:");
        ui.print(task.toString());
        ui.print(String.format("Now you have %d tasks in the list", taskList.numberOfTasks()));
    }
}
