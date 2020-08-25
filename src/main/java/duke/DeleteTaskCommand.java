package duke;

public class DeleteTaskCommand implements Command {
    TaskList tasks;
    int taskIndex;

    public DeleteTaskCommand(TaskList tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    public void execute() {
        tasks.deleteTask(taskIndex);
    }
}
