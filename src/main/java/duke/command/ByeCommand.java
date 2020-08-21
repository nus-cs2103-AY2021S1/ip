package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represent a "Bye" Command
 * A <code>ByeCommand</code> object corresponds to a command of an input "bye".
 */
public class ByeCommand extends Command {

    /**
     * Sets the variable isExit to true and prints the exit message.
     * It also writes and save the data to the datafile before exiting
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     * @throws DukeException Exception when there is an error updating the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        this.isExit = true;
        ui.exit();
        storage.update();
    }
}
