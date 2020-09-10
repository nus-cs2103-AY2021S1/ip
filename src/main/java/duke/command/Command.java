package duke.command;

import duke.main.TaskList;

/** Command is used to perform a or a number of operations on the given TaskList or just simply
 *  prints messages to the user.
 */
public abstract class Command {
    /**
     * Performs the desired operations.
     *
     * @param tasks The related TaskList.
     */
    public abstract void perform(TaskList tasks);

    /**
     * Gets the reply after performing the Command.
     *
     * @return A reply as a String based on the perform method.
     **/
    public abstract String getReply();

    /**
     * Checks if this Command is a termination Command.
     *
     * @return True if this Command is a termination Command, otherwise false.
     */
    public abstract boolean isExit();
}
