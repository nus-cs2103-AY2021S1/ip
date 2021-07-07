package duke;

public class DeleteTaskCommand implements Command {
    private TaskList tasks;
    private int taskIndex;

    /**
     * Deletes a task
     * @param tasks
     * @param taskIndex
     */
    public DeleteTaskCommand(TaskList tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes a task
     * @return
     */
    public String execute() {
        tasks.deleteTask(taskIndex);
        return "Deleted";
    }
}
