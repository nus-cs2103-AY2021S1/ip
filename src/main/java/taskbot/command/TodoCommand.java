package taskbot.command;

import taskbot.task.TaskList;

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

    /**
     * Gives instructions on how to use the todo command.
     * @return A string of instructions to use the command.
     */
    public static String getInstruction() {
        return "Adds a todo task to the tasks list\n"
                + "Format: todo [desc]\n"
                + "desc: The description of the todo task\n";
    }

    public String getTask() {
        return task;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.addTodoTask(task);
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
