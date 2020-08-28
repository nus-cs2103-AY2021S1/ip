package commands;

import duke.Storage;
import duke.Ui;
import exceptions.DukeException;
import tasks.TaskList;


/**
 * Represents a generic command entered by the user
 */
public class Command {

    /**
     * Placeholder command to be overridden by subclasses
     * @param tasks The current TaskList
     * @param ui The Ui object in use
     * @param storage The Storage object in use
     * @throws DukeException If erroneous inputs are detected
     */
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }
}
