package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

/**
 * Encapsulates a command to add a todo task.
 */
public class TodoCommand extends Command {
    // The task description
    private String task;

    /**
     * Creates a TodoCommand
     *
     * @param task The task description.
     */
    public TodoCommand(String task) {
        super(false);
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTodoTask(task);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof TodoCommand)) {
            return false;
        }

        // Compare tasks and return accordingly
        return task.equals(((TodoCommand) obj).getTask());
    }
}
