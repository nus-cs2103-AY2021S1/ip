package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * represents a command to add a task
 */
public abstract class AddTaskCommand extends Command {
    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    AddTaskCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    protected String addedTaskMessage(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("got it! i have added the following task to your list:\n    ")
                .append(task)
                .append("\n" + tasks.numberOfTasks());

        return sb.toString();
    }
}
