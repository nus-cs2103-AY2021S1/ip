package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand implements Command {
    /**
     * Prints a goodbye message to the user.
     *
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.displayGoodbye();
    }

    /**
     * Tells Duke to exit.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
