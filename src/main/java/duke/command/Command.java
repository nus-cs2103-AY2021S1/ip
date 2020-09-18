package duke.command;

import duke.task.TaskList;
import duke.dukeexception.DukeException;
import duke.Ui;
import duke.Storage;

/**
 * Contains the logic for executing commands of different types,
 * such as adding a <code>Task</code> to a <code>TaskList</code>.
 * Subclasses of <code>Command</code> may have fields containing required information like the
 * task description for adding a <code>Task</code>.
 */
public abstract class Command {
    public Command() {}

    /**
     * Returns a boolean indicating whether the app should close.
     *
     * @return  <code>true</code> for <code>TerminationCommand</code>, <code>false</code> otherwise
     */
    public abstract boolean isExit();

    /**
     * Deals with the logic behind any user command,
     * calling the relevant method(s) from <code>TaskList</code>
     * and returning the relevant message from <code>Ui</code>.
     *
     * @param taskList  Duke's current list of tasks
     * @param ui        Duke's initialised <code>Ui</code> object
     * @param storage   Duke's initialised <code>Storage</code> object
     * @return  a <code>String</code> of character lines and tasks depending on type of <code>Command</code>
     * @throws DukeException  if user input is wrong, like task number is not within range of taskList
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException;
}
