package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Creates a list command.
 */
public class ListCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of existing tasks.
     * @param ui      The ui that handles user interaction.
     * @param storage The storage that stores the list of existing tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks);
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
