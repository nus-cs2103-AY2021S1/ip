package main.command;

import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the exit command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.1
 */
public class ExitCommand implements Command {

    /**
     * Does not execute anything.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @return the string bidding farewell to the user.
     */
    @Override
    public String execute(Ui ui, TaskList tasks) {
        return ui.printExitMessage();
    }

    /**
     * Returns false as there are no more commands after this.
     * @return false.
     */
    @Override
    public boolean hasCommandAfter() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
