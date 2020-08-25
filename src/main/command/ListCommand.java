package main.command;

import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the list command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class ListCommand implements Command {

    /**
     * Prints out the entire task list via the ui.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     */
    @Override
    public void execute(Ui ui, TaskList tasks) {
        ui.printTaskList(tasks);
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommand() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
