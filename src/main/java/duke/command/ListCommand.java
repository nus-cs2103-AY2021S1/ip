package duke.command;

import duke.Storage;
import duke.Ui;
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
     * @param ui The Ui object that is used by Duke.
     * @param storage The Storage object of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.displayMessage("No tasks on your agenda.");
        } else {
            ui.displayMessage(String.format("Here are the tasks in your agenda:\n%s", tasks.listTasks()));
        }
    }
}
