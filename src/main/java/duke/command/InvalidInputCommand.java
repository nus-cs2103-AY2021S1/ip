package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printInvalidInput();
    }
}
