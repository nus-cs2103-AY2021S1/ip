package duke;

public class CreateTaskCommand implements Command {
    TaskList tasks;
    Task task;
    public CreateTaskCommand(TaskList tasks, Task task) {
        this.tasks = tasks;
        this.task = task;
    }

    @Override
    public String execute() {
        String message = String.format("Added: %s", task.toString());
        tasks.addTask(task);
        return message;
    }
}
