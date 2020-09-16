package duke;

public class CreateTaskCommand implements Command {
    private final TaskList tasks;
    private final Task task;

    /**
     * Construct create task command
     * @param tasks
     * @param task
     */
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
