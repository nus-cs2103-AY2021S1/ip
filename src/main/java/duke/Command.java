package duke;

import java.io.IOException;

/**
 * Implements the parent structure of all Commands
 */
public class Command {
    boolean isExit = false;

    public Command() {
    }

    /**
     * Getter for isExit
     * @return boolean to indicate whether or not to break the main loop in Duke class
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Passes the Tasks in TaskList to Storage to update to hard disk
     * @param tasks TaskList containing Tasks
     * @param ui Ui object that handles printing of any necessary output
     * @param storage Storage object that handles saving Tasks to hard disk
     * @throws DukeException
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        storage.update(tasks);
    }
}
