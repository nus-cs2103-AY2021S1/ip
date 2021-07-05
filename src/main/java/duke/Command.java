package duke;

import java.io.IOException;

/**
 * Represents an executable command received by Duke.
 */
public abstract class Command {

    /**
     * Returns true if command is an exit command.
     * 
     * @return True if command is an exit command and false otherwise.
     */
    public boolean isExit() {
        return isExit();
    }

    /**
     * Executes the command and returns the corresponding message to the user.
     * 
     * @param taskList TaskList of the user.
     * @param ui The Ui instance responsible for interacting with the user.
     * @param storage The storage instance responsible for loading and storing the tasks.
     * @return String representation of the response of Duke.
     * @throws DukeException If command is invalid.
     * @throws IOException If tasks cannot be saved.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
