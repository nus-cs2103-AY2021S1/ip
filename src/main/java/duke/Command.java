package duke;

import java.io.IOException;

/**
 * Represents an abstract Command class.
 */
public abstract class Command {

    /**
     * Abstract method to be implemented by all subclasses of Command.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @throws DukeException When execution fails.
     * @throws IOException When execution fails.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui)
            throws DukeException, IOException;

}
