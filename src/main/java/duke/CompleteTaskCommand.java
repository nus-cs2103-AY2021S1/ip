package duke;

public class CompleteTaskCommand implements Command {
    private final TaskList tasks;
    private final int taskIndex;

    /**
     * Construct task
     * @param tasks
     * @param taskIndex
     */
    public CompleteTaskCommand(TaskList tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    public String execute() {
        return tasks.completeTask(taskIndex);
    }
}
