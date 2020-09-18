package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Abstract class to define a general command processing class.
 */
public abstract class Command {
    protected String attributes;

    /**
     * Execute the command's process (CLI version).
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return true if program should continue after process is executed
     * @throws DukeException Duke-related exception due to processing
     */
    public abstract boolean runCLI(TaskList taskList, Storage storage, Ui ui) throws DukeException;

    /**
     * Execute the command's process (GUI version).
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return true if program should continue after process is executed
     * @throws DukeException Duke-related exception due to processing
     */
    public abstract String runGUI(TaskList taskList, Storage storage, Ui ui) throws DukeException;
}