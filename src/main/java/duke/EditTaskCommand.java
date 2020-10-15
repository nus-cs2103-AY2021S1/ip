package duke;

public class EditTaskCommand implements Command {
    private final TaskList tasks;
    private final Task task;
    private final Integer taskIndex;

    /**
     * Construct create task command
     * @param tasks
     * @param task
     */
    public EditTaskCommand(TaskList tasks, Integer taskGuiIndex, Task task) {
        this.tasks = tasks;
        this.task = task;
        this.taskIndex = taskGuiIndex - 1;
    }

    @Override
    public String execute() {
        // Update the task
        tasks.updateTask(taskIndex, task);
        String message = String.format("Updated: %d %s", taskIndex + 1, task.toString());
        return message;
    }
}
