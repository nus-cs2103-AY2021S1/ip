package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Encapsulates a command to handle an invalid input
 */
public class InvalidInputCommand extends Command {

    /**
     * Executes the command to handle an invalid input
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.getInvalidInputStrings();
    }
}
