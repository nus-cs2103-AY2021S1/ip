package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a {@link Command} that will display all pending tasks.
 */
public class PendingCommand extends Command {
    /**
     * Overrides execute in {@link Command}.
     * Executes the command to display all pending tasks.
     *
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.showPendingTasks();
    }
}
