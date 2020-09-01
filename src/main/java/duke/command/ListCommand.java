package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a {@link Command} that will display all tasks.
 */
public class ListCommand extends Command {
    /**
     * Overrides execute in {@link Command}.
     * Executes the command to display all tasks.
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getSize() == 0) {
            return "No tasks on your agenda.";
        } else {
            return String.format("Here are the tasks in your agenda:\n%s", tasks.listTasks());
        }
    }
}
