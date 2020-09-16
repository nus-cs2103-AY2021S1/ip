package dude.command;

import dude.ui.Ui;
import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;

/**
 * The command signals for the bot to terminate.
 */

public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand class.
     *
     * @param action action the command is to perform.
     */
    public ExitCommand(String action) {
        super(action);
    }

    /**
     * Adds the task to the TaskList, stores the data and displays the resulting output.
     *
     * @param tasks TaskList containing all the current tasks.
     * @param ui Ui class to display output.
     * @param storage Storage class to store tasks.
     * @throws CommandException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        willExit = true;
        ui.sendOff();
    }
}
