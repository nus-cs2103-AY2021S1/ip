package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {
    /**
     * Prints out the list of the user's tasks.
     *
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     * @return String of user's task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.displayTaskList(tasks);
    }

    /**
     * Tells Duke to continue running.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
