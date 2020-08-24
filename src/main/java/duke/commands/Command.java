package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents a user command. Each individual command should extend
 * from this abstract class and implement the execute method.
 */
public abstract class Command {

    /** Executes the command. This method will be implemented by the child classes.
     *
     * @param taskList The taskList involved.
     * @param ui The ui involved to show messages to the user.
     */
    public abstract void execute(TaskList taskList, Ui ui);

    /** Represents whether the program is exiting.
     *
     * @return False by default.
     */
    public boolean isExit() {
        return false;
    }
}