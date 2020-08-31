package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a {@link Command} that will display all completed tasks.
 */
public class CompletedCommand extends Command {
    /**
     * Overrides execute in {@link Command}.
     * Executes the command to display all completed tasks.
     * @param tasks The list of {@link Task}s.
     * @param ui The Ui object that is used by Duke.
     * @param storage The Storage object of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(tasks.showCompletedTasks());
    }
}
