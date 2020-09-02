package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


/**
 * Corresponds to commands the users wants to execute.
 */
public abstract class Command {
    /**
     * Uses polymorphism to execute the methods relevant to the Command.
     *
     * @param tasks       TaskList object which contains the updated list.
     * @param ui          The reference to ui, for updating the ui.
     * @param fileHandler Contains methods to update the file.
     * @throws DukeException Throws DukeException which must be caught by the method.
     */
    public abstract void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException;
}
