package duke;

public class DeleteTaskCommand implements Command {
    TaskList tasks;
    int taskIndex;

    public DeleteTaskCommand(TaskList tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    public String execute() {
        tasks.deleteTask(taskIndex);
        return "Deleted";
    }
}
