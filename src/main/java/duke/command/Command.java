package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.tasklist.TaskList;

import duke.ui.Ui;

/**
 * Handles all possible actions of the program, as given by the user input.
 */
public abstract class Command {
    /**
     * Executes a specific command.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws DukeException If an error is found in the execution of command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Provides a boolean to which whether the program can stop.
     *
     * @return False as the default value.
     */
    public boolean isExit() {
        return false;
    }
}
