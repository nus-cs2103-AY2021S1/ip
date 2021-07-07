package duke.commands;

import duke.util.OutputUi;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.DukeException;

import java.io.IOException;

/**
 * Implements the parent structure of all Commands.
 */
public class Command {
    boolean isExit = false;

    public Command() {
    }

    /**
     * Getter for isExit.
     * @return boolean to indicate whether or not to break the main loop in Duke class.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Passes the Tasks in TaskList to Storage to update to hard disk.
     * @param tasks TaskList containing Tasks.
     * @param ui Ui object that handles printing of any necessary output.
     * @param storage Storage object that handles saving Tasks to hard disk.
     * @throws DukeException DukeException.
     * @throws IOException IOException.
     */
    public String execute(TaskList tasks, OutputUi ui, Storage storage) throws DukeException {
        storage.update(tasks);

        return "command received";
    }
}
