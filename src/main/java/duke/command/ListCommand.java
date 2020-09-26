package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a {@link Command} that will display all tasks.
 */
public class ListCommand extends Command {
    private static final String NO_TASKS_MESSAGE = "No tasks on your agenda.";
    private static final String LIST_TASKS_MESSAGE = "Here are the tasks in your agenda:";

    /**
     * Overrides execute in {@link Command}.
     * Executes the command to display all tasks.
     *
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getSize() == 0) {
            return NO_TASKS_MESSAGE;
        } else {
            return String.format(LIST_TASKS_MESSAGE + "\n%s", tasks.listTasks());
        }
    }
}
