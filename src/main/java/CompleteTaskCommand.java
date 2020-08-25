public class CompleteTaskCommand implements Command {
    TaskList tasks;
    int taskIndex;
    public CompleteTaskCommand(TaskList tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    public void execute() {
        tasks.completeTask(taskIndex);
    }
}
