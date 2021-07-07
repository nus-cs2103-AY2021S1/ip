package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to handle an invalid input
 */
public class InvalidInputCommand extends Command {

    /**
     * Executes the command to handle an invalid input.
     *
     * @param storage Storage
     * @param taskList Task list
     * @param archive
     * @param ui Ui
     * @return Output strings displayed on the UI signifying invalid input
     */
    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        assert ui != null;

        return ui.getInvalidInputStrings();
    }
}
