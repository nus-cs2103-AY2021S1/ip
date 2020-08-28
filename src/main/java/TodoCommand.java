package main.java;

/**
 * handles the "todo" commands
 */
public class TodoCommand extends Command {

    /**
     * Constructor for TodoCommand
     *
     * @param description
     * @throws IllegalArgumentException
     */
    public TodoCommand(String description) throws IllegalArgumentException {
        super(description);
    }

    /**
     * shows the task added, updates the total number of tasks
     *
     * @param taskList
     */
    public void execute(TaskList taskList) {
        Todo newTask = new Todo(this.getTaskName());
        taskList.addTask(newTask);
        TextUi.printNewTasks(newTask.toString());
        TextUi.printTaskSummary(taskList.getTaskLength());
    }
}
