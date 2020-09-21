package duke.command;

import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the HelpCommand by adding the duke.task in to the list
     * and giving user messages. The storage file is also updated.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @return Duke's response.
     * @throws InvalidInputException If the input is invalid.
     */

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        return ui.displayHelp();
    }

    /**
     * Checks if the command is ExitCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
